package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @ClassName AuthorizationServerConfig
 * @Description 授权服务器配置
 * @Author Jay.Jia
 * @Date 2020/4/6 5:45
 * @Version 1.0
 */
@Configuration
//认证服务器注解
@EnableAuthorizationServer
public class AuthorizationSecurityServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * @Description 配置Token的存储方式:Redis
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/4/6 5:48
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean
    public TokenStore tokenStore() {
        return new MyRedisTokenStore(connectionFactory);
    }

    /**
     * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @Param [endpoints] 
     * @Author Jay.Jia    
     * @Date 2020/4/6 6:28 
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置tokenStore
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore()).
                allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }

    /**
     * AuthorizationServerSecurityConfigurer 用来配置令牌端点(Token Endpoint)的安全约束
     * @Param
     * @Author Jay.Jia
     * @Date 2020/4/6 6:28
     * @return
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置token获取合验证时的策略,允许表单认证
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 这里是客户端信息配置:
     * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     *  1.授权码模式（authorization code）
     *  2.简化模式（implicit）
     *  3.密码模式（resource owner password credentials）
     *  4.客户端模式（client credentials）
     * @Param [clients] 
     * @Author Jay.Jia    
     * @Date 2020/4/6 6:29 
     * @return void
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        clients.
//                jdbc(dataSource).
        inMemory().
                withClient("jay-web")
                .resourceIds("jay-web")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all","read", "write","aa")
                .authorities("client_credentials")
                .secret(finalSecret)
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000)
                .and()
                //这里配置了密码模式
                .withClient("jay-zuul")
                .resourceIds("jay-zuul")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("server")
                .authorities("password")
                .secret(finalSecret)
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }
}
