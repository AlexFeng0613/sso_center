package com.hsjc.central.interceptor;

import com.hsjc.central.annotation.SystemLog;
import com.hsjc.central.mapper.RestfulLogMapper;
import com.hsjc.central.mapper.SystemLogMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zga
 * @date : 2015-12-14
 *
 * 日志拦截器
 */
@Component
@Aspect
public class LogInterCeptor {
    private Logger logger = Logger.getLogger(LogInterCeptor.class);

    @Autowired
    private RestfulLogMapper restfulLogMapper;

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Pointcut("@annotation(com.hsjc.central.annotation.SystemLog)")
    public  void controllerAspect() {
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 操作异常记录
     *
     * @param point
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint point, Throwable e) {

    }

    @After("controllerAspect()")
    public void doAfterInvoke(JoinPoint joinPoint){

    }

    public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("actionId", method.getAnnotation(SystemLog.class).actionId());
                    String de = method.getAnnotation(SystemLog.class).description();
                    if(StringUtils.isEmpty(de)) de="执行成功!";
                    map.put("description", de);
                    break;
                }
            }
        }
        return map;
    }


}
