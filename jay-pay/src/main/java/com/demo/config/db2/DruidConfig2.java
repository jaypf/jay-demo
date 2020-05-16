package com.demo.config.db2;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 1:31
 * @Version 1.0
 */
@Slf4j
@Data
@Configuration
@PropertySource(value = "classpath:application.yml")
//@ConfigurationProperties(prefix = "spring.druid",ignoreInvalidFields = true)
@MapperScan(basePackages = "com.demo.mapper.db2", sqlSessionFactoryRef = "twoSqlSessionFactory")
public class DruidConfig2 {
    // 配置数据源
    @Bean(name = "twoDataSource")
    public DataSource testDataSource(DBConfig2 testConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(testConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(testConfig.getPassword());
        mysqlXaDataSource.setUser(testConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("test2DataSource");

        xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(testConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定mapper xml目录,如果不指定会报org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)异常
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapperxml/**/*.xml"));
        return bean.getObject();
    }

}
