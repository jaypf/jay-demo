package com.demo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName DynamicDataSourceAspect
 *
 * //        * @Aspect->作用是把当前类标识为一个切面供容器读取
 * //        * @Pointcut->Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
 * //        *  方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，
 * //        *  因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * //        * @Around->环绕增强，相当于MethodInterceptor
 * //        * @AfterReturning->后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * //        * @Before->标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * //        * @AfterThrowing->异常抛出增强，相当于ThrowsAdvice
 * //        * @After-> final增强，不管是抛出异常或者正常退出都会执行
 *
 * @Author Jay.Jia
 * @Date 2020/5/14 18:06
 * @Version 1.0
 */
@Slf4j

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) {
        //这个就是数据源标识
        String dsId = ds.name();

        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error("数据源【{}】不存在，使用默认数据源 > {}",
                    ds.name(),
                    point.getSignature());
        }
        else {
            log.debug("Use dataSource : {} > {}",
                    ds.name(),
                    point.getSignature());
            //如果容器中有数据源，那么就把数据源标识设置到ThreadLocal中
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    @After("@annotation(ds)")
    public void releaseLocal(JoinPoint point, TargetDataSource ds) {
        log.info("==释放ds：" + ds.name() + "的ThreadLocal绑定==");
        if(DynamicDataSourceContextHolder.getDataSourceType() != null) {
            DynamicDataSourceContextHolder.getContextHolder().remove();
        }
    }
}
