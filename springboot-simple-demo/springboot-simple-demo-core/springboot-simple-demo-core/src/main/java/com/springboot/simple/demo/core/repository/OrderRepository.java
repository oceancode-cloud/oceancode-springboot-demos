/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository;

import com.oceancode.cloud.api.repository.ActionCallback;
import com.oceancode.cloud.api.repository.Rollback;
import com.oceancode.cloud.common.entity.*;
import com.springboot.simple.demo.core.domain.Order;

import java.util.List;
import java.util.Set;

/**
 * <B>OrderRepository</B>
 *
 * <p>
 * Generic interface that defines the Domain(Order) common to all.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface OrderRepository {
    /**
     * find one record by primaryKey from table(order).
     *
     * @param id primaryKey
     * @return if it is successful return Order object else return null
     */
    Order findById(Long id, boolean throwEx);
    Order findById(Long id);

    /**
     * find records by primaryKeys from table(order).
     *
     * @param ids primaryKeys
     * @return if it is successful return List<Order> object else return empty list.
     */
    List<Order> findByIds(Set<Long> ids);
    List<Order> findByIds(Set<Long> ids, boolean throwEx);

    List<Order> findByIds(Long id, Long...ids);

    /**
     * insert one record to table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    boolean addOne(Order entity);
    boolean addOne(Order entity, boolean throwEx);

    /**
     * delete one record by primaryKey from table(order).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    boolean deleteById(Long id, boolean throwEx);
    boolean deleteById(Long id);

    /**
     * update one record based on non-empty fields from table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    boolean updateById(Order entity);

    boolean updateById(Order entity, boolean throwEx);

    boolean updateBatchById(List<Order> list);

    boolean updateBatchById(List<Order> list, int batchSize);

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

    com.springboot.simple.demo.core.domain.Order queryUserOrderByOrderId(Long orderId);

}
