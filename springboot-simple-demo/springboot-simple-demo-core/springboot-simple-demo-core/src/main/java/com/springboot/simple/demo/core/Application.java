/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core;

import com.oceancode.cloud.common.FullModelBeanNameGenerator;
import com.oceancode.cloud.common.util.SystemUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackageClasses = {
        com.springboot.simple.demo.core.PackageInfo.class,
        com.oceancode.cloud.common.PackageInfo.class
}, nameGenerator = FullModelBeanNameGenerator.class)
public abstract class Application {

    public <T extends Application> void run(Class<T> primarySource, String[] args) {
        SpringApplication springApplication = new SpringApplication(primarySource);
        ConfigurableApplicationContext ctx = springApplication.run(args);
        SystemUtil.init();
    }
}