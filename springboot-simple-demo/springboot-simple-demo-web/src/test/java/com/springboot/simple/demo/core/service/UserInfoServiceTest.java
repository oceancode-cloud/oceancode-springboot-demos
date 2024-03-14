package com.springboot.simple.demo.core.service;

import com.springboot.simple.demo.core.entity.UserInfo;
import com.springboot.simple.demo.core.util.entity.UserInfoes;
import com.springboot.simple.demo.web.AppMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserInfoServiceTest {

    @Test
    public void test() {
        UserInfo userInfo = UserInfoes.newUser()
                .withAge(23)
                .get();
    }
}
