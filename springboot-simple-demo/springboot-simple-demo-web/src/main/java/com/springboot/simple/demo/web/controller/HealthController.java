package com.springboot.simple.demo.web.controller;

import com.oceancode.cloud.api.permission.Permission;
import com.oceancode.cloud.api.permission.PermissionConst;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/api/login")
    @Permission(resourceId = "api_login", authorities = {PermissionConst.AUTHORITY_UN_LOGIN})
    public String ping2() {
        return "OK";
    }
}
