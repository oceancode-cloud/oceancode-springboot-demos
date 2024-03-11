/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.entity.api.result;

import com.oceancode.cloud.api.Transferable;

/**
 * <B>UserBaseInfo</B>
 *
 * <p>
 * This class is a Output which is output parameters.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class UserBaseInfo implements Transferable {
    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 账号
     */
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
