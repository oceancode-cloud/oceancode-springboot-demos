/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.common.datasource;

import com.oceancode.cloud.api.security.Rsa2CryptoService;
import com.oceancode.cloud.common.config.DatasourceWrapper;
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

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = CommonConst.MAPPER_PACKAGE + ".slave1", sqlSessionFactoryRef = "slave1SqlSessionFactory")
public class Slave1DatasourceConfig {
    @Resource
    private Rsa2CryptoService rsa2CryptoService;

    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource masterDataSource() {
        DatasourceWrapper dataSource = new DatasourceWrapper(rsa2CryptoService,"slave1");
        dataSource.setName("slave1DataSource-master");
        return dataSource;
    }

    @Bean(name = "slave1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slave1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:repository/slave1/*.xml")
        );
        bean.setTypeHandlersPackage(UniversalEnumHandler.class.getPackage().getName());
        return bean.getObject();
    }

    @Bean(name = "slave1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slave1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "slave1NamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("slave1DataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "slave1PlatformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("slave1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}