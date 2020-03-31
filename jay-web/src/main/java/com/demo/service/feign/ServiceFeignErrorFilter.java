package com.demo.service.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @ClassName ServiceFeignErrorFilter
 * @Description
 * 这个过滤器是对异常信息的再封装，把 feign 的异常信息封装成我们系统的通用异常对象
 * 过滤器把异常返回后，feign 定义的降级方法就会调到 ServiceFeignFallbackFactory的create 方法。
 * @Author Jay.Jia
 * @Date 2020/3/31 11:00
 * @Version 1.0
 */
@Configuration
public class ServiceFeignErrorFilter {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }


    /**
     * @Description
     * 当调用服务时，如果服务返回的状态码不是200，就会进入到Feign的ErrorDecoder中
     * eg:{"timestamp":"2020-03-31T03:25:39.850+0000","status":400,"error":"Bad Request","message":"Required Integer parameter 'ms' is not present","path":"/product/v1/getAllProduct"}
     * 只有这种方式才能获取所有的被feign包装过的异常信息
     *
     * 这里如果创建的Exception是HystrixBadRequestException
     * 则不会走熔断逻辑，不记入熔断统计
     * @Param
     * @Author Jay.Jia
     * @Date 2020/3/31 11:03
     * @return
     **/
    class FeignErrorDecoder implements ErrorDecoder {

        private Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

        @Override
        public Exception decode(String s, Response response) {

            RuntimeException runtimeException = null;

            try {
                String retMsg = Util.toString(response.body().asReader());
                logger.error("ServiceFeignErrorFilter FIND ERROR---->"+retMsg);
                //可以处理为项目约定的错误体
                /*JSONObject jsonObject = JSONObject.parseObject(retMsg);
                Integer status = (Integer)jsonObject.get("status");
                jsonObject.put("status",4000);
                retMsg = jsonObject.toString();*/

                runtimeException = new RuntimeException(retMsg);

            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            return runtimeException;
        }
    }

}
