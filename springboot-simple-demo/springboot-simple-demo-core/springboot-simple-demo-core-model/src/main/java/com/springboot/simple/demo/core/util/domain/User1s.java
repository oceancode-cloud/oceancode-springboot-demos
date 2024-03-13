/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.util.domain;

import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.ValueUtil;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.springboot.simple.demo.core.domain.User1;



import java.util.Objects;

public final class User1s {
    private User1s() {
    }

    public static User1 copy(User1 model) {
        User1 temp = new User1();
        temp.setId(model.getId());
        temp.setPassword(model.getPassword());
        temp.setUsername(model.getUsername());
        return temp;
    }

    public static Builder newUser() {
        return new Builder();
    }

    public final static class Builder {
        private User1 model;

        private Builder() {
            this.model = new User1();
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

        public User1 get() {
            return this.model;
        }
    }

}
