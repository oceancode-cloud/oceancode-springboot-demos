/**
 * Copyright (C) NA Technologies Co., Ltd. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.web.api;

import com.oceancode.cloud.api.cache.RedisCacheService;
import com.oceancode.cloud.common.config.CommonConfig;
import com.oceancode.cloud.common.util.JsonUtil;
import com.oceancode.cloud.common.util.RedisUtil;
import com.oceancode.cloud.common.util.ValueUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@ConditionalOnBean(RedisCacheService.class)
public class LettuceConfig {

    @Resource
    private CommonConfig commonConfig;

    @Bean("redisRedismasterPool")
    @ConfigurationProperties(prefix = "spring.redis.master.lettuce.pool")
    public GenericObjectPoolConfig redisPool0() {
        return new GenericObjectPoolConfig();
    }

    @Bean("redisRedismasterConfig")
    @Primary
    @ConditionalOnProperty(name = "spring.redis.master.mode", havingValue = "cluster", matchIfMissing = true)
    public RedisClusterConfiguration redisClusterConfig0() {
        Map<String, Object> source = new HashMap<>(8);
        source.put("spring.redis.cluster.nodes", commonConfig.getValue("spring.redis.master.cluster.nodes"));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        String password = commonConfig.getValue("spring.redis.master.password");
        if (ValueUtil.isNotEmpty(password)) {
            redisClusterConfiguration.setPassword(password);
        }

        return redisClusterConfiguration;
    }

    @Bean("redisRedismasterConfig")
    @ConfigurationProperties(prefix = "spring.redis.master")
    @ConditionalOnProperty(name = "spring.redis.master.mode", havingValue = "standalone", matchIfMissing = false)
    public RedisStandaloneConfiguration redisConfig0() {
        return new RedisStandaloneConfiguration();
    }

    @Bean("lettuceRedismasterConnectionFactory")
    @ConditionalOnProperty(name = "spring.redis.master.mode", havingValue = "standalone", matchIfMissing = false)
    public LettuceConnectionFactory redisRedismasterFactory(@Qualifier("redisRedismasterPool") GenericObjectPoolConfig config,
                                                            @Qualifier("redisRedismasterConfig") RedisStandaloneConfiguration redisConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig, clientConfiguration);
    }

    @Bean("masterRedisTemplate")
    public RedisTemplate<String, Object> redisRedismasterTemplate(@Qualifier("lettuceRedismasterConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory) {
        return RedisUtil.builderRedisTemplate(lettuceConnectionFactory);
    }
}