package com.springboot.simple.demo.core.service;

import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.PasswordUtil;
import com.springboot.simple.demo.core.domain.User;
import com.springboot.simple.demo.core.enums.UserStatus;
import com.springboot.simple.demo.web.AppMain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserServiceTest {

    @Resource
    private UserService userService;

    private static User createUser() {
        User user = new User();
        user.setUsername("1354174190");
        user.setPassword(PasswordUtil.encode("qinjiawang"));
        user.setEmail("testqjw@sina.com");
        user.setStatus(UserStatus.NORMAL);
        return user;
    }

    @Test
    public void addUser() {
        User user = createUser();

        /*
        1. 使用时统一使用XXXService，如此处UserService。
        2. userService.repository(),userService.repository(ture) 当有租户或工作区等业务字段时会进行数据隔离。
        3. userService.repository(false) 原始数据操作，不区分组合和工作区等业务字段。
         */
        userService.repository().addOne(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findById() {
        User user = userService.repository().findById(4L);
        Assert.assertEquals("qinjiawang", user.getUsername());
    }

    @Test(expected = BusinessRuntimeException.class)
    public void findById_not_found() {
        /**
         * 数据不存在，会自动抛出异常，在web环境下会抛出对应的错误码，无需用户代码进行判断。
         * 减少代码量同事也避免执行后续代码
         */
        User user = userService.repository().findById(400L);
    }

    @Test
    public void findById_not_found2() {
        /**
         * 手动处理数据不存在的情况
         */
        User user = userService.repository().findById(400L, false);
        Assert.assertNull(user);
    }
}
