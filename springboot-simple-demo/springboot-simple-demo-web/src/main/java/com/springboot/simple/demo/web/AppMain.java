package com.springboot.simple.demo.web;

import com.springboot.simple.demo.core.Application;
import com.oceancode.cloud.common.FullModelBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackageClasses = {
                com.springboot.simple.demo.web.PackageInfo.class,
                com.springboot.simple.demo.core.PackageInfo.class,
},
        nameGenerator = FullModelBeanNameGenerator.class
)
public class AppMain extends Application {

    public static void main(String[] args) {
        new AppMain().run(AppMain.class, args);
    }
}
