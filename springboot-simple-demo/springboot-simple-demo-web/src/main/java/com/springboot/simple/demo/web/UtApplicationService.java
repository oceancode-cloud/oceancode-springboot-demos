package com.springboot.simple.demo.web;

import com.oceancode.cloud.api.ApplicationLifeCycleService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class UtApplicationService implements ApplicationLifeCycleService {
    @Resource(name = "masterDataSource")
    private DataSource dataSource;

    public ResourceDatabasePopulator resourceDatabasePopulator(){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/schema/db.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/schema/data.sql"));
        return resourceDatabasePopulator;
    }

    @Override
    public void onReady() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator());
        dataSourceInitializer.afterPropertiesSet();
    }
}
