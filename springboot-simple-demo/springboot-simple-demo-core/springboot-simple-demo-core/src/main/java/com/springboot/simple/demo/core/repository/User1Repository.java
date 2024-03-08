/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository;

import com.oceancode.cloud.api.repository.ActionCallback;
import com.oceancode.cloud.api.repository.Rollback;
import com.oceancode.cloud.common.entity.*;
import com.springboot.simple.demo.core.domain.User1;

import java.util.List;
import java.util.Set;

/**
 * <B>User1Repository</B>
 *
 * <p>
 * Generic interface that defines the Domain(User1) common to all.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface User1Repository {
    /**
     * find one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return User1 object else return null
     */
    User1 findById(Long id, boolean throwEx);
    User1 findById(Long id);

    /**
     * find records by primaryKeys from table(t_user).
     *
     * @param ids primaryKeys
     * @return if it is successful return List<User1> object else return empty list.
     */
    List<User1> findByIds(Set<Long> ids);
    List<User1> findByIds(Set<Long> ids, boolean throwEx);

    List<User1> findByIds(Long id, Long...ids);

    /**
     * insert one record to table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return 1 else return 0
     */
    boolean addOne(User1 entity);
    boolean addOne(User1 entity, boolean throwEx);

    /**
     * delete one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    boolean deleteById(Long id, boolean throwEx);
    boolean deleteById(Long id);

    /**
     * update one record based on non-empty fields from table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return 1 else return 0
     */
    boolean updateById(User1 entity);

    boolean updateById(User1 entity, boolean throwEx);

    boolean updateBatchById(List<User1> list);

    boolean updateBatchById(List<User1> list, int batchSize);

    boolean existsById(Long id);

    boolean existsByIds(Set<Long> ids);

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
