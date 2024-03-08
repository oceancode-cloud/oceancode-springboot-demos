/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.springboot.simple.demo.core.common.constant.CommonConst;
import com.springboot.simple.demo.core.common.handler.UniversalEnumHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = CommonConst.MAPPER_PACKAGE + ".master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDatasourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setName("masterDataSource-master");
        return dataSource;
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:repository/master/*.xml")
        );
        bean.setTypeHandlersPackage(UniversalEnumHandler.class.getPackage().getName());
        return bean.getObject();
    }

    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "masterNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "masterPlatformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
