/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service.impl;

import com.springboot.simple.demo.core.repository.OrderRepository;
import com.springboot.simple.demo.core.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.*;

/**
 * <B>OrderServiceImpl</B>
 *
 * <p>
 * This class implement OrderService.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Component
public class OrderServiceImpl implements OrderService {
    @Resource
    private com.springboot.simple.demo.core.repository.business.BusinessOrderRepository businessRepository;

    @Resource
    private OrderRepository repository;

    /**
     * get OrderRepository instance
     * <p>You can this method directly to call OrderRepository,rather than direct call OrderRepository.</p>
     *
     * @return OrderRepository
     */
    @Override
    public OrderRepository repository() {
        return businessRepository;
    }

    /**
     * get OrderRepository instance
     * <p>You can this method directly to call OrderRepository,rather than direct call OrderRepository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return OrderRepository
     */
    @Override
    public OrderRepository repository(boolean useBusinessRepository) {
        return useBusinessRepository ? businessRepository : repository;
    }
}
