package com.springboot.simple.demo.web;

import com.oceancode.cloud.api.permission.PermissionResourceService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionResourceServiceImpl implements PermissionResourceService {
    @Override
    public Set<String> getAuthorities(String s) {
        Set<String> permission = new HashSet<>();
        permission.add("unlogin");
        return permission;
    }
}
