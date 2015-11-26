package com.hsjc.central.config;

import com.hsjc.central.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * @author : zga
 * @date : 15/11/24
 */
@Configuration
@ComponentScan({"com.hsjc"})
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableSpringDataWebSupport
@PropertySources({
	@PropertySource(value = "classpath:application.development.properties"),
	@PropertySource(value = "classpath:log4j.properties")
})
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

}
