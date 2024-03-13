# 用户登录(user-login 分支)
提供在单机版和分布式下的用户登录会话管理。

1. 单机版使用HttpSession.
2. Caffeine环境下使用caffeine存储登录信息。
3. Redis环境下使用Redis存储会话信息。
4. 用户登录互斥。
5. token生成加入客户端信息，防盗用。
6. 如果存在Redis环境以及其他环境，则默认有限使用redis作为会话缓存。

## 使用
1. 单机版使用HttpSession，只需springboot本身。
2. 使用caffeine作为缓存。需导入依赖
```xml
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
</dependency>
```
3. 使用redis 需导入
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

4. 使用SessionService接口
```java
public UserLoginResponse userLogin(UserLoginParam param) {
    UserBaseInfo userBaseInfo = new UserBaseInfo();
    userBaseInfo.setUserId(1L);
    userBaseInfo.setOpenid("123");
    userBaseInfo.addParam("username", "zhangsan").addParam("nickname", "张三");
    TokenInfo token = TokenUtil.createToken(userBaseInfo.getUserId(), userBaseInfo.getOpenid());

    sessionService.setUserInfo(token.getSessionId(), userBaseInfo);
    UserLoginResponse userLoginResponse = new UserLoginResponse();
    userLoginResponse.setToken(token.getToken());
    return userLoginResponse;
} 
```

5. 配置缓存key
```properties
# jwt token密钥
os.session.token.secret=oceancode
# 会话使用的缓存key
os.session.cache.key=user-session-info

# 缓存key key-pattern格式为  XXX...:#{#userId}:#{#token} 一定要有:#{#userId}:#{#token}
oc.cache.user-session-info.key-pattern=session:uid:#{#userId}:#{#token}
# 如果为redis则为对应数据源id，其他则写master即可
oc.cache.user-session-info.source.id=master

# redis配置
spring.redis.master.mode=standalone
spring.redis.master.host=127.0.0.1
spring.redis.master.port=6379
spring.redis.master.lettuce.pool.max-active=6000
spring.redis.master.lettuce.pool.max-idle=100
spring.redis.master.lettuce.pool.max-wait=100
spring.redis.master.lettuce.pool.min-idle= 50
spring.redis.master.lettuce.pool.timeout=500000
```