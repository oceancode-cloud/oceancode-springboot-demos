package com.springboot.simple.demo.core.service;

import com.springboot.simple.demo.core.domain.User1;
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
public class User1ServiceTest {

    @Resource
    private User1Service user1Service;

    @Test
    public void findById() {
        /*
        User1表跟User表一样，只是数据源不同
        此处业务代码是唯一需要编写的，数据源配置，mapper等代码均可生成
         */
        User1 user1 = user1Service.repository().findById(1L);
        Assert.assertEquals("qinjiawang", user1.getUsername());
    }
}
