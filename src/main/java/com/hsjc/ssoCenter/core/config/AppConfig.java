package com.hsjc.ssoCenter.core.config;

import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.constant.MailConstant;
import com.hsjc.ssoCenter.core.constant.RedisConstant;
import com.hsjc.ssoCenter.core.constant.SMSConstant;
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

	@Bean
	public Constant Constant(
			@Value("${3rd.publicKey}") String secretKey,
			@Value("website.address")String websiteAddress){
		Constant.public_key = secretKey;
		Constant.websiteAddress = websiteAddress;
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

	@Bean
	public SMSConstant SMSConstant(
			@Value("${sms.appkey}")String APP_KEY,
			@Value("${sms.appsecret}")String APP_SECRET,
			@Value("${sms.signname}")String SIGN_NAME,
			@Value("${sms.templatecode}")String TEMPLATE_CODE,
			@Value("${sms.url}")String URL,
			@Value("${sms.type}")String TYPE

	){
		SMSConstant.APPKEY = APP_KEY;
		SMSConstant.APPSECRET = APP_SECRET;
		SMSConstant.SIGNNAME = SIGN_NAME;
		SMSConstant.TEMPLATECODE = TEMPLATE_CODE;
		SMSConstant.URL = URL;
		SMSConstant.TYPE  = TYPE;
		return null;
	}

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
