# 多数据源支持(multiple-db 分支)

在模型表基类中配置不同的数据源，即可生成对应的代码实现多数据源相关的配置。

本示例演示了h2和sqlite的多数据源支持，其他数据源同理。 

## 步骤
1. 新建sqlite数据源。
2. 创建sqlite数据库，新建db/db.db(与运行项目src同级)


3. 使用api

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class User1ServiceTest {

    @Resource
    private User1Service user1Service;

    @Test
    public void findById() {
        /*
        User1表跟User表一样，只是数据源不同
        此处业务代码是唯一需要编写的，数据源配置，mapper等代码均可生成
         */
        User1 user1 = user1Service.repository().findById(1L);
        Assert.assertEquals("qinjiawang", user1.getUsername());
    }
}
```
