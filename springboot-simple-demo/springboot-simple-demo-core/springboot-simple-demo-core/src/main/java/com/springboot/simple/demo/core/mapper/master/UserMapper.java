/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.mapper.master;

import com.springboot.simple.demo.core.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * <B>User</B>
 *
 * <p>
 * This class is a Mapper.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Mapper
public interface UserMapper {
    User selectByUniqueColumn(@Param("column") String column, @Param("columnValue") String value, @Param("entity") User entity);

    Boolean existsByUniqueColumn(@Param("column") String column, @Param("columnValue") String value, @Param("entity") User entity);

    /**
     * insert one record to table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    int insertOne(User entity);

    /**
     * batch insert records to table(t_user).
     *
     * @param list User objects
     * @return if it is successful return batchSize else return 0
     */
    int insertList(@Param("list") List<User> list);

    /**
     * batch insert records to table(t_user).
     *
     * @param list      User objects
     * @param createdAt create time
     * @param updatedAt update time
     * @return if it is successful return batchSize else return 0
     */
    int insertListWithBusinessField(@Param("list") List<User> list, @Param("createdAt") Timestamp createdAt, @Param("updatedAt") Timestamp updatedAt);

    /**
     * delete one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    int deleteById(Long id);

    int deleteByIdWithBusiness(User entity);


    /**
     * batch delete one record by primaryKey from table(t_user).
     *
     * @param list primaryKeys
     * @return if it is successful return batchSize else return 0
     */
    int deleteByIds(@Param("list") Set<Long> list);

    int deleteSoftByIdsWithBusinessField(@Param("list") Set<Long> list, @Param("session") User session);


    /**
     * delete records based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    int delete(User entity);

    /**
     * delete one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    int deleteOne(User entity);

    /**
     * update one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    int updateById(User entity);
    int updateByIdWithBusiness(User entity);

    /**
     * find one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return User object else return null
     */
    User selectById(Long id);
    User selectByIdWithBusiness(User entity);

    /**
     * find records by primaryKeys from table(t_user).
     *
     * @param list primaryKeys
     * @return if it is successful return List<User> object else return empty list.
     */
    List<User> selectByIds(@Param("list") Set<Long> list);

    /**
     * find one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return User object else return null
     */
    User selectOne(User entity);

    /**
     * count records based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return the number of datasets else return 0
     */
    long selectCount(User entity);

    /**
     * find all datasets base on non-empty fields from table(t_user).
     * <p>Do not call when there is a larger amount of data</p>
     *
     * @return if it is successful return all records else return empty list.
     */
    List<User> selectAll();

    List<User> selectAllByEntity(User entity);
    /**
     * Pagination Query records base on non-empty fields from table(t_user).
     *
     * @param entity User instance that you can use it to filter useful data.
     * @return if it is successful return one-page data(PageResult<User>) else return empty PageResult.
     */
    List<User> selectList(@Param("offset") Integer offset, @Param("size") int size, @Param("entity") User entity);

    int updateBatchById(@Param("list") Set<User> list);

    Integer existsById(long id);
    int existsByIds(@Param("list") Set<Long> list);
    /**
     * queryAllUser
     *

     * @param session user session info(userId,projectId,tenantId)
     * @return User
     */
    List<com.springboot.simple.demo.core.domain.User> queryAllUser(@Param("session") java.util.Map<String,Object> sessionMap);

    /**
     * 根据邮箱查询用户
     *
     * @param entity  User
     * @param session user session info(userId,projectId,tenantId)
     * @return User
     */
    com.springboot.simple.demo.core.domain.User queryByEmail(@Param("email") String email, @Param("session") java.util.Map<String,Object> sessionMap);

    /**
     * 根据用户ID查询
     *
     * @param entity  User
     * @param session user session info(userId,projectId,tenantId)
     * @return User
     */
    com.springboot.simple.demo.core.domain.User queryByUserId(@Param("id") Long id, @Param("session") java.util.Map<String,Object> sessionMap);

}
