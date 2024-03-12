package com.demo.user.function.impl;

import com.springboot.simple.demo.core.entity.api.params.UserLoginParam;
import com.springboot.simple.demo.core.function.api.user.UserFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFunctionImpl implements UserFunction {

    @Override
    public void userLogin(UserLoginParam param) {
        System.out.println();
    }
}
