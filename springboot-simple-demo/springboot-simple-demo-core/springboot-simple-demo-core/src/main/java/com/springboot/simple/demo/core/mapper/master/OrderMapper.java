/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.mapper.master;

import com.springboot.simple.demo.core.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * <B>Order</B>
 *
 * <p>
 * This class is a Mapper.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Mapper
public interface OrderMapper {
    /**
     * insert one record to table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    int insertOne(Order entity);

    /**
     * batch insert records to table(order).
     *
     * @param list      Order objects
     * @param createdAt create time
     * @param updatedAt update time
     * @return if it is successful return batchSize else return 0
     */
    int insertListWithBusinessField(@Param("list") List<Order> list, @Param("createdAt") Timestamp createdAt, @Param("updatedAt") Timestamp updatedAt);

    /**
     * delete one record by primaryKey from table(order).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    int deleteById(Long id);

    int deleteByIdWithBusiness(Order entity);



    /**
     * delete one record based on non-empty fields from table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    int deleteOne(Order entity);

    /**
     * update one record based on non-empty fields from table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    int updateById(Order entity);
    int updateByIdWithBusiness(Order entity);

    /**
     * find one record by primaryKey from table(order).
     *
     * @param id primaryKey
     * @return if it is successful return Order object else return null
     */
    Order selectById(Long id);
    Order selectByIdWithBusiness(Order entity);

    /**
     * find records by primaryKeys from table(order).
     *
     * @param list primaryKeys
     * @return if it is successful return List<Order> object else return empty list.
     */
    List<Order> selectByIds(@Param("list") Set<Long> list);

    /**
     * find all records by primaryKeys and business fields(eg: userId,projectId,tenantId)
     *
     * @param list        primaryKeys
     * @param session     param entity
     * @return List<Order>
     */
    List<Order> selectByIdsWithBusinessField(@Param("list") Set<Long> list, @Param("session") Order session);

    /**
     * find one record based on non-empty fields from table(order).
     *
     * @param entity Order object
     * @return if it is successful return Order object else return null
     */
    Order selectOne(Order entity);

    int updateBatchById(@Param("list") Set<Order> list);

    Integer existsById(long id);
    int existsByIds(@Param("list") Set<Long> list);
    /**
     * 根据订单号查询用户订单
     *
     * @param entity  Order
     * @param session user session info(userId,projectId,tenantId)
     * @return Order
     */
    com.springboot.simple.demo.core.domain.Order queryUserOrderByOrderId(@Param("orderId") Long orderId, @Param("session") java.util.Map<String,Object> sessionMap);

}
