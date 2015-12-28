package com.hsjc.central.annotation;
  
import java.lang.annotation.*;

/**
 * @author : zga
 * @date : 2015-12-14
 *
 * 自定义注解 拦截Controller
 */  
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
	int actionId(); //执行方法Id
	String module() default "";
	String description()  default "";  //
}
  
