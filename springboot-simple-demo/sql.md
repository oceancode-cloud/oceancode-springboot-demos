# 自定义sql

提供自定义sql功能，类似与自定义mapper中的方法。

## usage
1. 在sql管理中创建sql，指定参数和返回值。自动生成对应方法。
2. 可根据数据源的不同实现多套sql,编译时会根据当前使用的数据源机型匹配。

特点：
1. 提供一些高级功能，屏蔽一些数据源的差异性。如使用模型属性字段替代数据库字段。
2. 参数注入，根据不同的中间件适配不同的形式，如mybaties为 #{}格式，springboot的动态参数， ":arg".如#{userId},:userId等多种格式。
3. 提供上下文参数，session.[userId,projectId,tenantId]
### sql举例

```sql
select ${ctx.result.id},${ctx.result.username},${ctx.result.email},${ctx.result.status} fom ${ctx.table.name} where email=${ctx.params.id}
```
${ctx.resut.X} 会自动匹配数据库种的字段
${ctx.params.id} 会根据框架和环境不同，生成不同的参数格式，如mybatis下为#{id},springboot下为:id 

解析结果：
```sql
select `id`,`username`,`email`,`status` fom `t_user` where email=#{id}
```

### 优势
1. 相比mybatis来说只需写一个sql关联表模型即可，无需创建Mapper.xml,Mapper.java,Respository.java等相关文件。
2. 可提供不同数据源下的实现，会自动根据当前表模型使用的数据源进行匹配。
3. 提供对sql规范的检查，如批量接口中纯在铭感数据自动的返回。
4. 屏蔽对底层数据层框架细节，如更换其他框架可做到用户无感知。
5. 名命统一，避免编写者名称差异过大。

示例参考：UserRepository中的queryByEmail，queryByUserId