package com.hsjc.central.config;


import com.hsjc.central.authrealm.MyAuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 安全配置类
 */
@Configuration
public class SecurityConfig {
	/**
	 * ehcache
	 * @return
	 */

	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager());
		return ehCacheManager;
	}

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager cacheManager() {
		return net.sf.ehcache.CacheManager.newInstance();
	}

	/**
	 * 凭证匹配器
	 * @return
     */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	/**
	 * 安全管理器
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myAuthRealm());
		securityManager.setCacheManager(ehCacheManager());
		securityManager.setSessionManager(defaultWebSessionManager());
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 自定义Realm
	 * @return
     */
	@Bean
	public MyAuthRealm myAuthRealm() {
		MyAuthRealm myAuthRealm = new MyAuthRealm();
		myAuthRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		myAuthRealm.setCachingEnabled(false);
		return myAuthRealm;
	}

	/**
	 * 配置shiro的过滤器工厂类
	 * @return
     */
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/user/login.html");

		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/code.html", "anon");
		filterChainDefinitionMap.put("/page/register/*.html", "anon");
		filterChainDefinitionMap.put("/page/logout.html", "logout");
		filterChainDefinitionMap.put("/user/login.html", "anon");
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;

	}

	/**
	 * 会话ID生成器
	 * @return
     */
	@Bean
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * 会话Cookie模板
	 * @return
     */
	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("sid");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}

	/**
	 * 持久cookie设置
	 * @return
     */
	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rm");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}

	/**
	 * rememberMe管理器
	 * @return
     */
	@Bean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		rememberMeManager.setCookie(rememberMeCookie());
		return rememberMeManager;
	}

	/**
	 * 会话DAO
	 * @return
     */
	@Bean
	public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(){
		EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
		enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		enterpriseCacheSessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator());

		return enterpriseCacheSessionDAO;
	}

	/**
	 * 会话验证调度器
	 * 全局的会话信息检测扫描信息间隔30分钟
	 * @return
     */
	@Bean
	public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
		QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
		quartzSessionValidationScheduler.setSessionValidationInterval(1800000);
		quartzSessionValidationScheduler.setSessionManager(defaultWebSessionManager());

		return quartzSessionValidationScheduler;
	}

	/**
	 * 会话管理器
	 * 全局的会话信息设置成30分钟,sessionValidationSchedulerEnabled参数就是是否开启扫描
	 * @return
     */
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager(){
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setGlobalSessionTimeout(1800000);
		defaultWebSessionManager.setDeleteInvalidSessions(true);
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		//defaultWebSessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler());
		defaultWebSessionManager.setSessionDAO(enterpriseCacheSessionDAO());
		defaultWebSessionManager.setSessionIdCookieEnabled(true);
		defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());

		return defaultWebSessionManager;
	}

	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

		return defaultAdvisorAutoProxyCreator;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor LifecycleBeanPostProcessor(){
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public LogoutFilter logout() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/user/login.html");
		return logoutFilter;
	}

}
