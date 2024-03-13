package com.springboot.simple.demo.web.api;

import com.oceancode.cloud.api.permission.Permission;
import com.oceancode.cloud.api.permission.PermissionConst;
import com.oceancode.cloud.api.session.SessionService;
import com.oceancode.cloud.common.constant.CommonConst;
import com.oceancode.cloud.common.entity.ResultData;
import com.oceancode.cloud.common.util.ComponentUtil;
import com.oceancode.cloud.common.web.util.ApiUtil;
import com.springboot.simple.demo.core.api.ApiDefConst;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <B>ApiController</B>
 *
 * <p>
 * This class is a Restfull Api.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@javax.annotation.Generated(
        value = "by tool generated (version 1.0.0)",
        comments = "Auto Generated"
)
@RestController
public class ApiController {
    /**
     * J_USER_001_0001 用户登录
     *
     * @return Object
     */
    @Permission(resourceId = ApiDefConst.J_USER_001_0001, operation = PermissionConst.OPERATION_OR, authorities = {"unlogin",})
    @org.springframework.web.bind.annotation.PostMapping(CommonConst.API_PREFIX + ApiDefConst.J_USER_001_0001)
    public Object userLogin(@org.springframework.web.bind.annotation.RequestBody com.springboot.simple.demo.core.entity.api.params.UserLoginParam param) {
        Object result = ComponentUtil
                .getFunction(com.springboot.simple.demo.core.function.api.user.UserFunction.class)
                .userLogin(param);
        return ResultData.isOk(result);
    }

    @Resource
    private SessionService sessionService;

    @GetMapping(CommonConst.API_PREFIX + "J_USR_000_0002")
    @Permission(resourceId = "J_USR_000_0002", operation = PermissionConst.OPERATION_OR, authorities = {"login",})
    public Object getUserInfo() {
        return sessionService.getUserInfo(ApiUtil.getToken());
    }

}
