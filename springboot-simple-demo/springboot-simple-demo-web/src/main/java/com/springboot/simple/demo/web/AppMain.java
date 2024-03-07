package com.springboot.simple.demo.web;

import com.oceancode.cloud.common.FullModelBeanNameGenerator;
import com.springboot.simple.demo.core.Application;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackageClasses = {
                PackageInfo.class,
                com.springboot.simple.demo.core.PackageInfo.class,
},
        nameGenerator = FullModelBeanNameGenerator.class
)
public class AppMain extends Application {

    public static void main(String[] args) {
        new AppMain().run(AppMain.class, args);
    }
}
