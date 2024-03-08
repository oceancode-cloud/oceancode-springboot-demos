/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service.impl;

import com.springboot.simple.demo.core.repository.User1Repository;
import com.springboot.simple.demo.core.service.User1Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.*;

/**
 * <B>User1ServiceImpl</B>
 *
 * <p>
 * This class implement User1Service.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Component
public class User1ServiceImpl implements User1Service {
    @Resource
    private User1Repository repository;

    /**
     * get User1Repository instance
     * <p>You can this method directly to call User1Repository,rather than direct call User1Repository.</p>
     *
     * @return User1Repository
     */
    @Override
    public User1Repository repository() {
        return repository;
    }

    /**
     * get User1Repository instance
     * <p>You can this method directly to call User1Repository,rather than direct call User1Repository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return User1Repository
     */
    @Override
    public User1Repository repository(boolean useBusinessRepository) {
        return repository;
    }
}
