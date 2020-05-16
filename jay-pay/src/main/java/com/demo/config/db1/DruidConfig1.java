package com.demo.config.db1;

import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName DruidConfig
 * @Description 测试atomikos数据源1
 * @Author Jay.Jia
 * @Date 2020/5/17 1:19
 * @Version 1.0
 */
@Slf4j
@Data
@Configuration
//@PropertySource(value = "classpath:application.yml")
//@ConfigurationProperties(prefix = "spring.druid",ignoreInvalidFields = true)
@MapperScan(basePackages = "com.demo.mapper.db1", sqlSessionFactoryRef = "oneSqlSessionFactory")
public class DruidConfig1 {
    @Primary                    //这个primary必须加，否则spring在两个sessionfactory的时候，不知道用哪个？
    // 配置数据源
    @Bean(name = "oneDataSource")
    public DataSource testDataSource(DBConfig1 testConfig) throws SQLException {
        // 这里直接针对mysql的分布式驱动，进行硬编码了
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(testConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(testConfig.getPassword());
        mysqlXaDataSource.setUser(testConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("testDataSource");    //硬编码，这里也是可以考虑落到配置文件中的

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

    @Primary
    @Bean(name = "oneSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定mapper xml目录,如果不指定会报org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)异常
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapperxml/**/*.xml"));
        return bean.getObject();
    }

}
