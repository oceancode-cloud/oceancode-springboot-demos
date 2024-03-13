/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.function.api.user;

import com.oceancode.cloud.function.RemoteFunction;
import com.springboot.simple.demo.core.entity.api.params.UserLoginParam;
import com.springboot.simple.demo.core.entity.api.result.UserLoginResponse;

/**
 * <B>UserFunction</B>
 * <p>The UserFunction interface of implements class.</p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@javax.annotation.Generated(
        value = "by tool generated (version 1.0.0)",
        comments = "Auto Generated"
)
public interface UserFunction extends RemoteFunction {
    /**
     * 账号密码登录
     *
     * @param password 登录密码
     * @param username 登录账号
     */
    UserLoginResponse userLogin(UserLoginParam param);

}
