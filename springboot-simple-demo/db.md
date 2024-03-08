# 数据库操作(helloword-db 分支)

创建一张用户表，生成对应的mapper,repository,service等方法。

1. 创建一个数据源，这里选择H2
2. 创建一个模型表(User 用户模型)
3. 生成对应的代码。

代码参考 springboot-simple-demo,db分支

## Service接口
1. 统一使用Service接口，Mapper,Repository接口不可直接使用。
2. 根据需要可指定是否使用带业务字段隔离(租户或工作区的隔离)。
3. 默认提供的Repository方法中保证了一定程度性能，安全性，铭感数据等处理，其他接口需手动选中后才会生成，使用时需自己关注性能问题。

### 默认生成接口有
1. findX  查询类。
2. addX 新增类
3. deleteX 删除类
4. updateX 修改类
5. existsX 存在判定类
6. transaction 事务

```java
User findById(Long id, boolean throwEx);
User findById(Long id);
List<User> findByIds(Set<Long> ids);
List<User> findByIds(Set<Long> ids, boolean throwEx);
List<User> findByIds(Long id, Long...ids);
boolean addOne(User entity);
boolean addOne(User entity, boolean throwEx);
boolean deleteById(Long id, boolean throwEx);
boolean deleteById(Long id);
boolean updateById(User entity);
boolean updateById(User entity, boolean throwEx);
boolean updateBatchById(List<User> list);
boolean updateBatchById(List<User> list, int batchSize);
boolean existsById(Long id);
boolean existsByIds(Set<Long> ids);
<T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback);
<T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback, boolean throwEx);
<T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback);
<T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback, boolean throwEx);
```

### 特性性生成

根据模型的特征生成对应的一些便捷方法，如唯一性字段会生成findByX等

```java
User findFieldByUsername(String username);
User findFieldByUsername(String username, boolean throwEx);
boolean existsFieldByUsername(String username);
boolean existsFieldByUsername(String username, boolean throwEx);
```

### 条件查询类

此类方法需要手动勾选后才会生成，需要使用者关注性能问题。适用于管理类的表，不适用于大数据量表。

> 从安全和性能方面考虑，需手动添加，避免误用滥用。

```java
User findOne(User entity, boolean throwEx);
User findOne(User entity);
List<User> findAll();
List<User> findAll(boolean throwEx);
List<User> findAll(User entity);
List<User> findAll(User entity, boolean throwEx);
PageResult<User> findPage(int page, int size, User entity);
PageResult<User> findPage(int page, int size);
long findCount(User entity);
<T> List<T> findWithSql(String sql, StringTypeMap params, Class<T> typeClass);
List<java.util.Map<String, Object>> findWithSql(String sql, StringTypeMap params);
boolean addBatch(List<User> list);
boolean addBatch(List<User> list, int batchSize);
int deleteByIds(Set<Long> ids);
int delete(User entity);
boolean deleteOne(User entity);
boolean deleteOne(User entity, boolean throwEx);
boolean saveOne(User entity);
boolean saveBatch(List<User> list);
boolean saveBatch(List<User> list, int batchSize);
```

### 测试用例参考 UserServiceTest

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserServiceTest {

    @Resource
    private UserService userService;

    private static User createUser() {
        User user = new User();
        user.setUsername("1354174190");
        user.setPassword(PasswordUtil.encode("qinjiawang"));
        user.setEmail("testqjw@sina.com");
        user.setStatus(UserStatus.NORMAL);
        return user;
    }

    @Test
    public void addUser() {
        User user = createUser();

        /*
        1. 使用时统一使用XXXService，如此处UserService。
        2. userService.repository(),userService.repository(ture) 当有租户或工作区等业务字段时会进行数据隔离。
        3. userService.repository(false) 原始数据操作，不区分组合和工作区等业务字段。
         */
        userService.repository().addOne(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findById() {
        User user = userService.repository().findById(4L);
        Assert.assertEquals("qinjiawang", user.getUsername());
    }

    @Test(expected = BusinessRuntimeException.class)
    public void findById_not_found() {
        /**
         * 数据不存在，会自动抛出异常，在web环境下会抛出对应的错误码，无需用户代码进行判断。
         * 减少代码量同事也避免执行后续代码
         */
        User user = userService.repository().findById(400L);
    }

    @Test
    public void findById_not_found2() {
        /**
         * 手动处理数据不存在的情况
         */
        User user = userService.repository().findById(400L, false);
        Assert.assertNull(user);
    }
}
```