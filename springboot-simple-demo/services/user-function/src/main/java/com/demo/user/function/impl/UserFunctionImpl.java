package com.demo.user.function.impl;

import com.oceancode.cloud.annotation.Inject;
import com.oceancode.cloud.api.session.SessionService;
import com.oceancode.cloud.api.session.TokenInfo;
import com.oceancode.cloud.api.session.UserBaseInfo;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.oceancode.cloud.common.web.util.TokenUtil;
import com.springboot.simple.demo.core.entity.api.params.UserLoginParam;
import com.springboot.simple.demo.core.entity.api.result.UserLoginResponse;
import com.springboot.simple.demo.core.function.api.user.UserFunction;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFunctionImpl implements UserFunction {

    @Resource
    @Inject
    private SessionService sessionService;

    @Override
    public UserLoginResponse userLogin(UserLoginParam param) {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(1L);
        userBaseInfo.setOpenid("123");
        userBaseInfo.addParam("username", "zhangsan")
                .addParam("nickname", "张三");
        TokenInfo token = TokenUtil.createToken(userBaseInfo.getUserId(), userBaseInfo.getOpenid());

        sessionService.setUserInfo(token.getSessionId(), userBaseInfo);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token.getToken());
        return userLoginResponse;
    }
}
