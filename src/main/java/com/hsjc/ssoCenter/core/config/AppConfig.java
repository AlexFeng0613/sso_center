package com.hsjc.ssoCenter.core.config;

import com.hsjc.ssoCenter.core.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * @author : zga
 * @date : 2015-11-24
 *
 */
@Configuration
@ComponentScan({"com.hsjc"})
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public RedisConstant redisConstant(
			@Value("${redis.dictDatabase}") Integer DB_DICT
	) {
		RedisConstant.DB_DICT = DB_DICT;
		return null;
	}

	/*@Bean
	public Constant Constant(){
		return null;
	}

	@Bean
	public MailConstant MailConstant(){
		return null;
	}

	@Bean
	public SMSConstant SMSConstant(){
		return null;
	}*/

	@Configuration
	@Profile("development")
	@PropertySource("classpath:application.development.properties")
	static class Development {}

	@Configuration
	@Profile("log4j")
	@PropertySources({
			@PropertySource("classpath:application.development.properties"),
			@PropertySource("classpath:log4j.properties")
	})
	static class Log4j{}
}
