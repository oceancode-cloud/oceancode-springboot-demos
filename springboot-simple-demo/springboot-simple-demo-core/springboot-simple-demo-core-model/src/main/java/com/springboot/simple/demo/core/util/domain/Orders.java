/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.util.domain;

import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.ValueUtil;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.springboot.simple.demo.core.domain.Order;


import java.sql.Timestamp;

import java.util.Objects;

public final class Orders {
    private Orders() {
    }

    public static Order copy(Order model) {
        Order temp = new Order();
        temp.setCreatedAt(model.getCreatedAt());
        temp.setId(model.getId());
        temp.setName(model.getName());
        temp.setOrderId(model.getOrderId());
        temp.setProjectId(model.getProjectId());
        temp.setUpdatedAt(model.getUpdatedAt());
        temp.setUserId(model.getUserId());
        return temp;
    }

    public static Builder newUser() {
        return new Builder();
    }

    public final static class Builder {
        private Order model;

        private Builder() {
            this.model = new Order();
        }

        public Builder withCreatedAt(Timestamp createdAt) {
            this.model.setCreatedAt(createdAt);
            return this;
        }

        public Builder withCreatedAtIfNull(Timestamp createdAt) {
            if (Objects.nonNull(this.model.getCreatedAt())) {
                this.model.setCreatedAt(createdAt);
            }
            return this;
        }


        public Builder withCreatedAtIfEmpty(Timestamp createdAt) {
            if (ValueUtil.isNotEmpty(this.model.getCreatedAt())) {
                this.model.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder withId(Long id) {
            this.model.setId(id);
            return this;
        }

        public Builder withIdIfNull(Long id) {
            if (Objects.nonNull(this.model.getId())) {
                this.model.setId(id);
            }
            return this;
        }


        public Builder withIdIfEmpty(Long id) {
            if (ValueUtil.isNotEmpty(this.model.getId())) {
                this.model.setId(id);
            }
            return this;
        }

        public Builder withName(String name) {
            this.model.setName(name);
            return this;
        }

        public Builder withNameIfNull(String name) {
            if (Objects.nonNull(this.model.getName())) {
                this.model.setName(name);
            }
            return this;
        }


        public Builder withNameIfEmpty(String name) {
            if (ValueUtil.isNotEmpty(this.model.getName())) {
                this.model.setName(name);
            }
            return this;
        }

        public Builder withOrderId(Long orderId) {
            this.model.setOrderId(orderId);
            return this;
        }

        public Builder withOrderIdIfNull(Long orderId) {
            if (Objects.nonNull(this.model.getOrderId())) {
                this.model.setOrderId(orderId);
            }
            return this;
        }


        public Builder withOrderIdIfEmpty(Long orderId) {
            if (ValueUtil.isNotEmpty(this.model.getOrderId())) {
                this.model.setOrderId(orderId);
            }
            return this;
        }

        public Builder withProjectId(Long projectId) {
            this.model.setProjectId(projectId);
            return this;
        }

        public Builder withProjectIdIfNull(Long projectId) {
            if (Objects.nonNull(this.model.getProjectId())) {
                this.model.setProjectId(projectId);
            }
            return this;
        }


        public Builder withProjectIdIfEmpty(Long projectId) {
            if (ValueUtil.isNotEmpty(this.model.getProjectId())) {
                this.model.setProjectId(projectId);
            }
            return this;
        }

        public Builder withUpdatedAt(Timestamp updatedAt) {
            this.model.setUpdatedAt(updatedAt);
            return this;
        }

        public Builder withUpdatedAtIfNull(Timestamp updatedAt) {
            if (Objects.nonNull(this.model.getUpdatedAt())) {
                this.model.setUpdatedAt(updatedAt);
            }
            return this;
        }


        public Builder withUpdatedAtIfEmpty(Timestamp updatedAt) {
            if (ValueUtil.isNotEmpty(this.model.getUpdatedAt())) {
                this.model.setUpdatedAt(updatedAt);
            }
            return this;
        }

        public Builder withUserId(Long userId) {
            this.model.setUserId(userId);
            return this;
        }

        public Builder withUserIdIfNull(Long userId) {
            if (Objects.nonNull(this.model.getUserId())) {
                this.model.setUserId(userId);
            }
            return this;
        }


        public Builder withUserIdIfEmpty(Long userId) {
            if (ValueUtil.isNotEmpty(this.model.getUserId())) {
                this.model.setUserId(userId);
            }
            return this;
        }

        public Order get() {
            return this.model;
        }
    }

}
