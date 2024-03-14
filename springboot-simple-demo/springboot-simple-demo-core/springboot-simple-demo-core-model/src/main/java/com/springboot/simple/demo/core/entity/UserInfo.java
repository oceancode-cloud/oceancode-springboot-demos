/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.entity;



/**
 * <B>UserInfo</B>
 *
 * <p>
 * This class is a Entity.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class UserInfo  {
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 主键
     * 主键
     */
    private Long id;

    /**
     * 登录昵称
     */
    private String nickname;

    /**
     * 登录账号
     */
    private String username;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
