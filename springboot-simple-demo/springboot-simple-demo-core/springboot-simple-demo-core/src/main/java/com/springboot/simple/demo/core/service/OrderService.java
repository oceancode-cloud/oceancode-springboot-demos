/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service;

import com.springboot.simple.demo.core.repository.OrderRepository;

/**
 * <B>OrderService</B>
 *
 * <p>
 * This class is a Service that can be called.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface OrderService {
    /**
     * get OrderRepository instance
     * <p>You can this method directly to call OrderRepository,rather than direct call OrderRepository.</p>
     *
     * @return {@link OrderRepository}
     */
    OrderRepository repository();

    /**
     * get OrderRepository instance
     * <p>You can this method directly to call OrderRepository,rather than direct call OrderRepository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return {@link OrderRepository}
     */
    OrderRepository repository(boolean useBusinessRepository);
}
