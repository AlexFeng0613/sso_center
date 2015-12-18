package com.hsjc.central.config;

import com.hsjc.central.constant.Constant;
import com.hsjc.central.constant.MailConstant;
import com.hsjc.central.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * @author : zga
 * @date : 2015-11-24
 *
 *
 */
@Configuration
@ComponentScan({"com.hsjc"})
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableMongoRepositories({"com.hsjc.central.importMongoData.repository"})
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
			@Value("${redis.dictDatabase}") Integer DB_DICT,
			@Value("${3rd.publicKey}") String secretKey
	) {
		RedisConstant.DB_DICT = DB_DICT;
		Constant.public_key = secretKey;
		return null;
	}

	@Bean
	public MailConstant MailConstant(
			@Value("${mail.host}") String MAIL_HOST,
			@Value("${mail.port}") Integer MAIL_PORT,
			@Value("${mail.username}") String MAIL_USERNAME,
			@Value("${mail.password}") String MAIL_PASSWORD,
			@Value("${mail.from}") String MAIL_FORM
	){
		MailConstant.MAIL_HOST = MAIL_HOST;
		MailConstant.MAIL_PORT = MAIL_PORT;
		MailConstant.MAIL_USERNAME = MAIL_USERNAME;
		MailConstant.MAIL_PASSWORD = MAIL_PASSWORD;
		MailConstant.MAIL_FROM = MAIL_FORM;
		return null;
	}

}
