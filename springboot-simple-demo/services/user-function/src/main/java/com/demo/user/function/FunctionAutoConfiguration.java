package com.demo.user.function;

import com.demo.user.function.impl.UserFunctionImpl;
import com.springboot.simple.demo.core.function.api.user.UserFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionAutoConfiguration {
    @Bean
    public UserFunction userFunction() {
        return new UserFunctionImpl();
    }
}
