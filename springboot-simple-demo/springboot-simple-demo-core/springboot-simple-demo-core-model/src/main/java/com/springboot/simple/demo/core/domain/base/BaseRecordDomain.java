/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.domain.base;

import java.sql.Timestamp;

/**
 * <B>BaseRecordDomain</B>
 *
 * <p>
 * This class is a BaseDomain which is provided common fields.
 * </p>
 * <p>Usually, this class only contains basic types like <b>String</b>,<b>Long</b> and so on.</p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
public class BaseRecordDomain {
    private Timestamp createdAt;

    private Long id;

    private Timestamp updatedAt;


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
