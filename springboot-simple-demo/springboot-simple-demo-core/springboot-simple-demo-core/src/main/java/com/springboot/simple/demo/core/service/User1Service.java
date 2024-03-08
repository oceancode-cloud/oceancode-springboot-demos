/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.service;

import com.springboot.simple.demo.core.repository.User1Repository;

/**
 * <B>User1Service</B>
 *
 * <p>
 * This class is a Service that can be called.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public interface User1Service {
    /**
     * get User1Repository instance
     * <p>You can this method directly to call User1Repository,rather than direct call User1Repository.</p>
     *
     * @return {@link User1Repository}
     */
    User1Repository repository();

    /**
     * get User1Repository instance
     * <p>You can this method directly to call User1Repository,rather than direct call User1Repository.</p>
     *
     * @param useBusinessRepository if true then autofill projectId, userId, tenantId and so on else non-auto fill.
     * @return {@link User1Repository}
     */
    User1Repository repository(boolean useBusinessRepository);
}
