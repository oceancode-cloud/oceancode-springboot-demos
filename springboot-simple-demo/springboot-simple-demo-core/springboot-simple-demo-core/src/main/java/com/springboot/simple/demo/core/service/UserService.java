/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service;

import com.springboot.simple.demo.core.repository.UserRepository;

/**
 * <B>UserService</B>
 *
 * <p>
 * This class is a Service that can be called.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface UserService {
    /**
     * get UserRepository instance
     * <p>You can this method directly to call UserRepository,rather than direct call UserRepository.</p>
     *
     * @return {@link UserRepository}
     */
    UserRepository repository();

    /**
     * get UserRepository instance
     * <p>You can this method directly to call UserRepository,rather than direct call UserRepository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return {@link UserRepository}
     */
    UserRepository repository(boolean useBusinessRepository);
}
