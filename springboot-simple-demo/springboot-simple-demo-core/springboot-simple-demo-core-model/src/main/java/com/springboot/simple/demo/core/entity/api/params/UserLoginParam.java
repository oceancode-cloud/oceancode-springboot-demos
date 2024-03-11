/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.entity.api.params;

import com.oceancode.cloud.api.validation.Validator;
import com.oceancode.cloud.common.util.CheckApiParamUtil;

/**
 * <B>UserLoginParam</B>
 *
 * <p>
 * This class is a Input which is api input parameters.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class UserLoginParam implements Validator {
    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录账号
     */
    private String username;

    @Override
    public void validate() {
        CheckApiParamUtil.checkString(password, 32, "password", true);
        CheckApiParamUtil.checkString(username, 32, "username", true);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
