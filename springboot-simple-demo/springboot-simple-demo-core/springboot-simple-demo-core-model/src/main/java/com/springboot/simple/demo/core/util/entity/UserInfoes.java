/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.util.entity;

import com.oceancode.cloud.api.cache.CacheKey;
import com.oceancode.cloud.api.cache.CacheService;
import com.oceancode.cloud.common.cache.KeyParam;
import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.ComponentUtil;
import com.oceancode.cloud.common.util.ValueUtil;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.springboot.simple.demo.core.entity.UserInfo;



import java.util.Objects;

public final class UserInfoes {
    private UserInfoes() {
    }

    public static Cache cache() {
        CacheKey cacheKey = KeyParam.of("user-session-info");
        return new Cache(cacheKey);
    }

    public final static class Cache {
        private CacheKey cacheKey;

        public Cache(CacheKey cacheKey) {
            this.cacheKey = cacheKey;
        }

        public UserInfo get() {
            return ComponentUtil.getBean(CacheService.class)
                    .getEntity(cacheKey, UserInfo.class);
        }

        public void set(UserInfo model) {
            ComponentUtil.getBean(CacheService.class)
                    .setEntity(cacheKey, model);
        }
    }

    public static Builder newUser() {
        return new Builder();
    }

    public final static class Builder {
        private UserInfo model;

        private Builder() {
            this.model = new UserInfo();
        }

        public Builder withAge(Integer age) {
            this.model.setAge(age);
            return this;
        }

        public Builder withAgeIfNull(Integer age) {
            if (Objects.nonNull(this.model.getAge())) {
                this.model.setAge(age);
            }
            return this;
        }


        public Builder withAgeIfEmpty(Integer age) {
            if (ValueUtil.isNotEmpty(this.model.getAge())) {
                this.model.setAge(age);
            }
            return this;
        }

        public Builder withAvatar(String avatar) {
            this.model.setAvatar(avatar);
            return this;
        }

        public Builder withAvatarIfNull(String avatar) {
            if (Objects.nonNull(this.model.getAvatar())) {
                this.model.setAvatar(avatar);
            }
            return this;
        }


        public Builder withAvatarIfEmpty(String avatar) {
            if (ValueUtil.isNotEmpty(this.model.getAvatar())) {
                this.model.setAvatar(avatar);
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

        public Builder withNickname(String nickname) {
            this.model.setNickname(nickname);
            return this;
        }

        public Builder withNicknameIfNull(String nickname) {
            if (Objects.nonNull(this.model.getNickname())) {
                this.model.setNickname(nickname);
            }
            return this;
        }


        public Builder withNicknameIfEmpty(String nickname) {
            if (ValueUtil.isNotEmpty(this.model.getNickname())) {
                this.model.setNickname(nickname);
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

        public UserInfo get() {
            return this.model;
        }
    }

}
