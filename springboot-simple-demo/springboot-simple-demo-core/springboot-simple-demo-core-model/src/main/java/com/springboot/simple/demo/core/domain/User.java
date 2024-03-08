/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.domain;

import com.springboot.simple.demo.core.domain.base.*;
import com.springboot.simple.demo.core.enums.*;


/**
 * <B>User</B>
 *
 * <p>
 * This class is a Domain which is mapping to Table(t_user)
 * </p>
 *
 * <pre>
 *     Important:
 *        Prohibit direct serialization storage this class,
 *     because it may contain <font color="red"><I><B>"sensitive data"</B></I></font>
 *     that could lead to data leakage.
 * </pre>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class User extends BaseRecordDomain {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态
     */
    private UserStatus status;

    /**
     * 登录账号
     */
    private String username;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
