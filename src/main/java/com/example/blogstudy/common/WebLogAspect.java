package com.example.blogstudy.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.example.blogstudy.domain.dto.WebLog;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/10/29 10:30
 * @description:
 * @Aspect：用于定义切面
 * @Before：通知方法会在目标方法调用之前执行
 * @After：通知方法会在目标方法返回或抛出异常后执行
 * @AfterReturning：通知方法会在目标方法返回后执行
 * @AfterThrowing：通知方法会在目标方法抛出异常后执行
 * @Around：通知方法会将目标方法封装起来
 * @Pointcut：定义切点表达式
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数)
     * <p>
     * com.macro.mall.tiny.controller包中所有类的public方法都应用切面里的通知
     * execution(public * com.macro.mall.tiny.controller.*.*(..))
     * com.macro.mall.tiny.service包及其子包下所有类中的所有方法都应用切面里的通知
     * execution(* com.macro.mall.tiny.service..*.*(..))
     * com.macro.mall.tiny.service.PmsBrandService类中的所有方法都应用切面里的通知
     * execution(* com.macro.mall.tiny.service.PmsBrandService.*(..))
     */
    @Pointcut("execution(public * com.example.blogstudy.controller.*.*(..))")
    public void WebLog() {
    }

    @Before("WebLog()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("切点前执行Before方法");
    }

    @AfterReturning(value = "WebLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
        System.out.println("doAfterReturning方法会在目标方法返回后执行");
    }

    @Around("WebLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取当前对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求信息
        WebLog webLog = new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpentTime((int) (endTime - startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}", JSONUtil.parse(webLog));
        return result;
    }

    /**
     * 根据方法和传入参数获取请求参数
     *
     * @param method
     * @param args
     * @return
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }

            // 将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
}
