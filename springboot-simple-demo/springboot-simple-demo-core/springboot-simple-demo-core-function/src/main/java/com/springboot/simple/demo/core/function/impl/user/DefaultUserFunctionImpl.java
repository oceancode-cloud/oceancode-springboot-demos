/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.function.impl.user;

import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.ValueUtil;
import com.springboot.simple.demo.core.function.api.user.UserFunction;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <B>UserFunction</B>
 * <p>The {ctx.value.filename()} interface of implements class.</p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@javax.annotation.Generated(
        value = "by tool generated (version 1.0.0)",
        comments = "Auto Generated"
)
@Primary
@Component
public final class DefaultUserFunctionImpl implements UserFunction {
    @Resource
    private UserFunction _customerRemoteFunction;

    /**
     * 账号密码登录
     *
     * @param password 登录密码
     * @param username 登录账号

     */
    @Override
    public void userLogin(com.springboot.simple.demo.core.entity.api.params.UserLoginParam param) {
        param.validate();
        _customerRemoteFunction.userLogin(param);
    }

}
