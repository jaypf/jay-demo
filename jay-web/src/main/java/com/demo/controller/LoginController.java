package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.bean.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description 使用授权码模式时获取授权码
 * @Author Jay.Jia
 * @Date 2020/4/9 22:42
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    /** 请求Token地址 */
    @Value("${security.oauth2.client.access-token-uri}")
    private String oauthServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Description 获取授权码code(测试请用浏览器，因为要跳转)
     * @Param [response]
     * @Author Jay.Jia
     * @Date 2020/4/9 23:16
     * @return void
     **/
    @GetMapping("/toAuthLogin")
    public void toAuthLogin(HttpServletResponse response) throws IOException {
        StringBuilder redirectUrl = new StringBuilder("http://localhost:9490/auth/oauth/authorize?");
        redirectUrl.append("client_id=pc&");
        redirectUrl.append("redirect_uri=http://localhost:9190/login/callback&");
        redirectUrl.append("response_type=code&");
        //state参数传过去啥传回来啥，一般记录跳转之前的路径
        redirectUrl.append("state=/index");
        //本服务中这个value值取客户端 id 和客户端密码必须要经过 base64 算法加密，并且放到 header 中，加密模式为 Base64(clientId:clientPassword),
        response.addHeader("Authorization","Basic amF5LXp1dWw6MTIzNDU2");
        response.sendRedirect(redirectUrl.toString());
    }


    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam String code, String state , HttpSession session){
        log.info("获取授权码成功,code is {}, state is {}",code,state);

        //认证服务器验token地址 /oauth/check_token 是  spring.security.oauth2的验token端点，即配置的oauthServiceUrl
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//不是json请求
        //网关的appId(client_id)，appSecret(secret)，需要在数据库oauth_client_details注册
        headers.setBasicAuth("pc","123456");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        //授权码
        params.add("code",code);
        //授权类型-授权码模式
        params.add("grant_type","authorization_code");
        //认证服务器会对比数据库客户端信息的的redirect_uri和这里的是不是一致，不一致就报错
        params.add("redirect_uri","http://localhost:9190/login/callback");

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);
        ResponseEntity<AccessToken> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, AccessToken.class);

        session.setAttribute("token",response.getBody());

        return JSONObject.toJSONString(response.getBody());
    }

}
