/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.domain;

import com.springboot.simple.demo.core.domain.base.*;


/**
 * <B>User1</B>
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
public class User1 extends BaseRecordSlaveDomain {
    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录账号
     */
    private String username;


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
