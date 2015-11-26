package com.hsjc.central.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hsjc.central.base.FastJsonRedisSerializer;
import com.hsjc.central.constant.RedisConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : zga
 * @date : 15/11/24
 */
@Configuration
public class DataConfig {

	@Bean
	public DataSource dataSource(
			@Value("${db.driver}") String driver,
			@Value("${db.url}") String url,
			@Value("${db.username}") String username,
			@Value("${db.password}") String password
	) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);

		druidDataSource.setInitialSize(24);
		druidDataSource.setMinIdle(24);
		druidDataSource.setMaxActive(1024);
		druidDataSource.setMaxIdle(1024);
		druidDataSource.setMaxWait(60000);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(30000);
		druidDataSource.setValidationQuery("select 1");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

		try {
			druidDataSource.setFilters("wall,stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return druidDataSource;
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(@Value("${db.driver}") String driver,
																	 @Value("${db.url}") String url,
																	 @Value("${db.username}") String username,
																	 @Value("${db.password}") String password){
		DataSourceTransactionManager dataSourceTransactionManager  = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource(driver, url, username, password));
		return dataSourceTransactionManager;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.hello.model");
		final Resource configLocation = new ClassPathResource("mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(configLocation);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(@Value("${db.driver}") String driver,
														   @Value("${db.url}") String url,
														   @Value("${db.username}") String username,
														   @Value("${db.password}") String password) throws Exception{
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.hello.mapper");
		mapperScannerConfigurer.setSqlSessionFactory(sqlSessionFactory(dataSource(driver, url, username, password)));
		return mapperScannerConfigurer;
	}

	@Bean
	public JedisConnectionFactory dictJedisConnectionFactory(
			@Value("${redis.host}") String host,
			@Value("${redis.port}") int port,
			@Value("${redis.password}") String password
	) {
		JedisConnectionFactory factory = new JedisConnectionFactory();

		factory.setHostName(host);
		factory.setPort(port);
		if (!StringUtils.isEmpty(password)) {
			factory.setPassword(password);
		}
		factory.setDatabase(RedisConstant.DB_DICT);

		return factory;
	}

	private void fillRedisTemplateSerializers(RedisTemplate redisTemplate, Class redisClass) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(redisClass));
	}
}
