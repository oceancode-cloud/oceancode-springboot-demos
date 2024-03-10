package com.springboot.simple.demo.core.service;

import com.oceancode.cloud.common.util.SessionUtil;
import com.springboot.simple.demo.core.domain.Order;
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
public class OrderServiceTest {
    @Resource
    private OrderService orderService;

    @Test
    public void findById(){
        Order order = orderService.repository(false).findById(1L);
        Assert.assertNotNull(order);
    }

    @Test
    public void queryUserOrderByOrderId(){
        SessionUtil.setUserId(1L);
        Order order = orderService.repository().queryUserOrderByOrderId(1L);
        Assert.assertNotNull(order);
    }

}
