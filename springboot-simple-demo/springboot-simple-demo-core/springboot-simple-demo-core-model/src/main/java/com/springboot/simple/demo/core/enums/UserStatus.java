/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.enums;

import com.oceancode.cloud.api.TypeEnum;

/**
 * <B>UserStatus</B>
 * <p>账号状态</p>
 *
 * <p>
 * This class is a Enum.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public enum UserStatus implements TypeEnum<Integer> {
    /**
     * 冻结
     */
    FROZEN(0, "冻结", ""),

    /**
     * 正常
     */
    NORMAL(1, "正常", ""),

    ;
    /**
     * enum value
     * <p>You can map to the corresponding enumeration through value.</p>
     * <p>You can use value to convert enumeration by TypeEnum.from().</p>
     * <pre>
     *     TypeEnum.from("value",UserStatus.class)
     * </pre>
     */
    private final Integer value;

    /**
     * name is a label used to describe value
     */
    private final String name;

    /**
     * describe value
     */
    private final String desc;

    /**
     * get UserStatus from value
     *
     * @param value the enum value
     * @return UserStatus instance
     */
    public static UserStatus from(Integer value) {
        return TypeEnum.from(value, UserStatus.class);
    }

    public static UserStatus from(Integer value, UserStatus defaultValue) {
        return TypeEnum.from(value, UserStatus.class, defaultValue);
    }

    UserStatus(Integer value, String name, String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return desc;
    }
}
