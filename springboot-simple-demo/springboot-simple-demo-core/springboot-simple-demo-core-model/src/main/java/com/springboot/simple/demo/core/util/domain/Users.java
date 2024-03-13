/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.util.domain;

import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.ValueUtil;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.springboot.simple.demo.core.domain.User;

import com.springboot.simple.demo.core.enums.*;

import java.sql.Timestamp;

import java.util.Objects;

public final class Users {
    private Users() {
    }


    public static Builder newUser() {
        return new Builder();
    }

    public final static class Builder {
        private User model;

        private Builder() {
            this.model = new User();
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

        public Builder withEmail(String email) {
            this.model.setEmail(email);
            return this;
        }

        public Builder withEmailIfNull(String email) {
            if (Objects.nonNull(this.model.getEmail())) {
                this.model.setEmail(email);
            }
            return this;
        }


        public Builder withEmailIfEmpty(String email) {
            if (ValueUtil.isNotEmpty(this.model.getEmail())) {
                this.model.setEmail(email);
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

        public Builder withPassword(String password) {
            this.model.setPassword(password);
            return this;
        }

        public Builder withPasswordIfNull(String password) {
            if (Objects.nonNull(this.model.getPassword())) {
                this.model.setPassword(password);
            }
            return this;
        }


        public Builder withPasswordIfEmpty(String password) {
            if (ValueUtil.isNotEmpty(this.model.getPassword())) {
                this.model.setPassword(password);
            }
            return this;
        }

        public Builder withStatus(UserStatus status) {
            this.model.setStatus(status);
            return this;
        }

        public Builder withStatusIfNull(UserStatus status) {
            if (Objects.nonNull(this.model.getStatus())) {
                this.model.setStatus(status);
            }
            return this;
        }


        public Builder withStatusIfEmpty(UserStatus status) {
            if (ValueUtil.isNotEmpty(this.model.getStatus())) {
                this.model.setStatus(status);
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

        public Builder withUsername(String username) {
            this.model.setUsername(username);
            return this;
        }

        public Builder withUsernameIfNull(String username) {
            if (Objects.nonNull(this.model.getUsername())) {
                this.model.setUsername(username);
            }
            return this;
        }


        public Builder withUsernameIfEmpty(String username) {
            if (ValueUtil.isNotEmpty(this.model.getUsername())) {
                this.model.setUsername(username);
            }
            return this;
        }

        public User get() {
            return this.model;
        }
    }

}
