/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.entity.api.result;

import com.oceancode.cloud.api.Transferable;

/**
 * <B>UserLoginResponse</B>
 *
 * <p>
 * This class is a Output which is output parameters.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class UserLoginResponse implements Transferable {
    /**
     * token
     */
    private String token;

    /**
     * 用户基本信息
     */
    private UserBaseInfo userInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBaseInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBaseInfo userInfo) {
        this.userInfo = userInfo;
    }

}
