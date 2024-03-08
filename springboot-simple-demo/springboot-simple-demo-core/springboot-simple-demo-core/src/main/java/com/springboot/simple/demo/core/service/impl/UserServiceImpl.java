/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service.impl;

import com.springboot.simple.demo.core.repository.UserRepository;
import com.springboot.simple.demo.core.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.*;

/**
 * <B>UserServiceImpl</B>
 *
 * <p>
 * This class implement UserService.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Component
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository repository;

    /**
     * get UserRepository instance
     * <p>You can this method directly to call UserRepository,rather than direct call UserRepository.</p>
     *
     * @return UserRepository
     */
    @Override
    public UserRepository repository() {
        return repository;
    }

    /**
     * get UserRepository instance
     * <p>You can this method directly to call UserRepository,rather than direct call UserRepository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return UserRepository
     */
    @Override
    public UserRepository repository(boolean useBusinessRepository) {
        return repository;
    }
}
