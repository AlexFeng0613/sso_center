package com.hsjc.central.interceptor;

import com.hsjc.central.annotation.SystemLog;
import com.hsjc.central.mapper.RestfulLogMapper;
import com.hsjc.central.mapper.SystemLogMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zga
 * @date : 2015-12-14
 *
 * 日志拦截器
 */
@SuppressWarnings("ALL")
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
    public void doAroundInvoke(JoinPoint joinPoint){
        Map<String,Object> map = null;
        try {
            map = getControllerMethodDescription(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("actionTime", new Date());
        restfulLogMapper.insert(map);
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
                    map.put("clientId",arguments[0]);
                    String de = method.getAnnotation(SystemLog.class).description();
                    if(StringUtils.isEmpty(de)) de = method.getAnnotation(SystemLog.class).module() + ","
                            + method.getAnnotation(SystemLog.class).description()
                            +"同步成功!";
                    map.put("description", de);
                    break;
                }
            }
        }
        return map;
    }


}
