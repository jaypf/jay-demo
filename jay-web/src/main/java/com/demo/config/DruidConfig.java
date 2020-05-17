package com.demo.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.demo.datasource.DynamicDataSource;
import com.demo.datasource.DynamicDataSourceContextHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DruidConfig
 * @Description 自定义配置数据源
 * @Author Jay.Jia
 * @Date 2020/5/13 11:22
 * @Version 1.0
 */
@Slf4j
@Data
@Configuration
//@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "spring.druid",ignoreInvalidFields = true)
public class DruidConfig {

    private String driverClassName;
    private String jdbcUrl1;
    private String username;
    private String jdbcUrl2;
    private String password;
    private int maxActive;
    private int minIdle;
    private int initialSize;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private String publickey;

    /**
     * 这里需要注意默认是读取的application.properties配置文件。
     * 如果你的配置文件不在默认文件中。
     * 需要在类中引入配置文件例如：@PropertySource(value = "classpath:druid.properties")
     *  @Bean(value = "db1", destroyMethod = "close",initMethod = "init")
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/5/14 18:00
     * @return javax.sql.DataSource
     **/
    @Bean(destroyMethod = "close",initMethod = "init")
    public DataSource getDs1(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(jdbcUrl1);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            String decrypt = ConfigTools.decrypt(publickey, password);
            druidDataSource.setPassword(decrypt);
            druidDataSource.setFilters(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

    /**
     *  多数据源时第二个数据源
     *  这里需要注意默认是读取的application.properties配置文件。
     *  如果你的配置文件不在默认文件中。
     *  需要在类中引入配置文件例如：@PropertySource(value = "classpath:druid.properties")
     *   @Bean(value = "db2",destroyMethod = "close",initMethod = "init")
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/5/14 17:59
     * @return javax.sql.DataSource
     **/

    @Bean(destroyMethod = "close",initMethod = "init")
    public DataSource getDs2(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(jdbcUrl2);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            String decrypt = ConfigTools.decrypt(publickey, password);
            druidDataSource.setPassword(decrypt);
            druidDataSource.setFilters(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

    /**
     * @Description 开启多数据源时配置
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/5/14 17:59
     * @return javax.sql.DataSource
     **/
    @Bean
    public DataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        DataSource db1 = getDs1();
        targetDataSources.put("ds1", db1);
        targetDataSources.put("ds2", getDs2());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        //设置数据源集合
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源
        dynamicDataSource.setDefaultTargetDataSource(db1);

        DynamicDataSourceContextHolder.dataSourceIds.add("ds1");
        DynamicDataSourceContextHolder.dataSourceIds.add("ds2");
        return dynamicDataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory jaySqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapperxml/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置访问druid监控
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStateViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //初始化参数initParams
        //添加白名单
        servletRegistrationBean.addInitParameter("allow","");
        //添加ip黑名单
        servletRegistrationBean.addInitParameter("deny","192.168.16.111");
        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername","jay");
        servletRegistrationBean.addInitParameter("loginPassword","jay123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 过滤不需要监控的后缀
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
