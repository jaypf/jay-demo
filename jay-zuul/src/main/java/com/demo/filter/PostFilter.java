package com.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @ClassName PostFilter
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/3 18:09
 * @Version 1.0
 */
@Slf4j
//@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            HttpServletResponse response = ctx.getResponse();
            Collection<String> headerNames = response.getHeaderNames();
            if (!ObjectUtils.isEmpty(headerNames)){
                log.info("HeaderNames:"+headerNames.toArray().toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
