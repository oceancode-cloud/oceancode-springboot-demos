/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository;

import com.oceancode.cloud.api.repository.ActionCallback;
import com.oceancode.cloud.api.repository.Rollback;
import com.oceancode.cloud.common.entity.*;
import com.springboot.simple.demo.core.domain.User;

import java.util.List;
import java.util.Set;

/**
 * <B>UserRepository</B>
 *
 * <p>
 * Generic interface that defines the Domain(User) common to all.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface UserRepository {
    /**
     * find one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return User object else return null
     */
    User findById(Long id, boolean throwEx);
    User findById(Long id);

    User findFieldByUsername(String username);

    User findFieldByUsername(String username, boolean throwEx);

    default boolean existsFieldByUsername(String username) {
        return existsFieldByUsername(username, false);
    }

    boolean existsFieldByUsername(String username, boolean throwEx);

    /**
     * find records by primaryKeys from table(t_user).     * <p>
     * The following fields will not be returned in the result set</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     *
     * @param ids primaryKeys
     * @return if it is successful return List<User> object else return empty list.
     */
    List<User> findByIds(Set<Long> ids);
    List<User> findByIds(Set<Long> ids, boolean throwEx);

    List<User> findByIds(Long id, Long...ids);

    /**
     * find one record based on non-empty fields from table(t_user).     * <p>
     * The following fields cannot be used as query criteria</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     *
     * @param entity User object
     * @return if it is successful return User object else return null
     */
    User findOne(User entity, boolean throwEx);
    User findOne(User entity);

    /**
     * find all datasets from table(t_user).     * <p>
     * The following fields will not be returned in the result set</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     * <p>Do not call when there is a larger amount of data</p>
     *
     * @return if it is successful return all records else return empty list.
     */
    List<User> findAll();
    List<User> findAll(boolean throwEx);

    /**
     * find all datasets base on non-empty fields from table(t_user).     * <p>
     * The following fields cannot be used as query criteria</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>     * <p>
     * The following fields will not be returned in the result set</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     * <p>Do not call when there is a larger amount of data</p>
     *
     * @param entity User instance that you can use it to filter useful data.
     * @return if it is successful return all records else return empty list.
     */
    List<User> findAll(User entity);
    List<User> findAll(User entity, boolean throwEx);

    /**
     * Pagination Query records base on non-empty fields from table(t_user).     * <p>
     * The following fields cannot be used as query criteria</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>     * <p>
     * The following fields will not be returned in the result set</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     *
     * @param page   current page number
     * @param size   the total of page
     * @param entity User instance that you can use it to filter useful data.
     * @return if it is successful return one-page data(PageResult<User>) else return empty PageResult.
     */
    PageResult<User> findPage(int page, int size, User entity);

    PageResult<User> findPage(int page, int size);

    /**
     * count records based on non-empty fields from table(t_user).     * <p>
     * The following fields cannot be used as query criteria</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     *
     * @param entity User object
     * @return if it is successful return the number of datasets else return 0
     */
    long findCount(User entity);

    /**
     * execute native sql
     * <p>Not Recommended</>
     *
     * @param sql       native sql
     * @param params    sql need params
     * @param typeClass data type that you want to return.
     * @param <T>       data type
     * @return query result
     */
    <T> List<T> findWithSql(String sql, StringTypeMap params, Class<T> typeClass);

    /**
     * execute native sql
     * <p>Not Recommended</>
     *
     * @param sql    native sql
     * @param params sql need params
     * @return query result
     */
    List<java.util.Map<String, Object>> findWithSql(String sql, StringTypeMap params);

    /**
     * insert one record to table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    boolean addOne(User entity);
    boolean addOne(User entity, boolean throwEx);

    /**
     * batch insert records to table(t_user).
     *
     * @param list User objects
     * @return if it is successful return batchSize else return 0
     */
    boolean addBatch(List<User> list);

    /**
     * batch insert records to table(t_user).
     *
     * @param list      User objects
     * @param batchSize batch size
     * @return if it is successful return batchSize else return 0
     */
    boolean addBatch(List<User> list, int batchSize);

    /**
     * delete one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    boolean deleteById(Long id, boolean throwEx);
    boolean deleteById(Long id);

    /**
     * batch delete one record by primaryKey from table(t_user).
     *
     * @param ids primaryKeys
     * @return if it is successful return batchSize else return 0
     */
    int deleteByIds(Set<Long> ids);

    /**
     * delete records based on non-empty fields from table(t_user).     * <p>
     * The following fields cannot be used as query criteria</p>
     * <ol>
     * <li><B>password</B> sensitive field</li>
     * </ol>
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    int delete(User entity);

    boolean deleteOne(User entity);

    boolean deleteOne(User entity, boolean throwEx);

    /**
     * update one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    boolean updateById(User entity);

    boolean updateById(User entity, boolean throwEx);

    boolean updateBatchById(List<User> list);

    boolean updateBatchById(List<User> list, int batchSize);

    boolean existsById(Long id);

    boolean existsByIds(Set<Long> ids);

    boolean saveOne(User entity);

    boolean saveBatch(List<User> list);

    boolean saveBatch(List<User> list, int batchSize);

    /**
     * manual transaction control.
     * <p>you can use it to control transaction,when an abnormality occurs happen,it can auto rollback </p>
     *
     * @param actionCallback commit logical
     * @param rollback       rollback when an abnormality occurs happen.
     * @param <T>            data type that you expected.
     * @return T object
     */
    <T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback);

    <T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback, boolean throwEx);

    /**
     * manual transaction control.
     * <p>you can use it to control transaction,when an abnormality occurs happen,it can auto rollback </p>
     *
     * @param actionCallback commit logical
     * @return T object
     */
    <T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback);

    <T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback, boolean throwEx);

}
