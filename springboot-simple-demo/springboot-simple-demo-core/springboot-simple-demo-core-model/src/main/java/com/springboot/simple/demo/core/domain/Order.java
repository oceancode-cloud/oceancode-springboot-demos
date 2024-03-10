/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.domain;

import com.springboot.simple.demo.core.domain.base.*;


/**
 * <B>Order</B>
 *
 * <p>
 * This class is a Domain which is mapping to Table(order)
 * </p>
 *
 * <pre>
 *     Important:
 *        Prohibit direct serialization storage this class,
 *     because it may contain <font color="red"><I><B>"sensitive data"</B></I></font>
 *     that could lead to data leakage.
 * </pre>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class Order extends BaseProjectDomain {
    /**
     * 订单名称
     */
    private String name;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 下单用户
     */
    private Long userId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
