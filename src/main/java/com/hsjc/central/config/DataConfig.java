package com.hsjc.central.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hsjc.central.base.FastJsonRedisSerializer;
import com.hsjc.central.constant.RedisConstant;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 数据源配置类
 */
@Configuration
public class DataConfig {

	@Bean
	public DruidDataSource druidDataSource(
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
		dataSourceTransactionManager.setDataSource(druidDataSource(driver, url, username, password));
		return dataSourceTransactionManager;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Value("${db.driver}") String driver,
											   @Value("${db.url}") String url,
											   @Value("${db.username}") String username,
											   @Value("${db.password}") String password) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(druidDataSource(driver, url, username, password));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.hsjc.central.domain");
		final Resource configLocation = new ClassPathResource("mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(configLocation);
		return sqlSessionFactoryBean;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() throws Exception{
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.hsjc.central.mapper");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
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

	@Bean
	public RedisTemplate redisTemplate(
			JedisConnectionFactory dictJedisConnectionFactory
	) {
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(dictJedisConnectionFactory);

		return redisTemplate;
	}

	private void fillRedisTemplateSerializers(RedisTemplate redisTemplate, Class redisClass) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(redisClass));
	}

	@Bean
	public MongoTemplate mongoTemplate(
			@Value("${mongodb.servers}") String servers,
			@Value("${mongodb.dbName}") String dbName,
			@Value("${mongodb.username}") String username,
			@Value("${mongodb.password}") String password
	) throws UnknownHostException {
		MongoClientOptions options = MongoClientOptions.builder().build();

		Mongo mongo;

		if (servers.contains(",")) {
			List<ServerAddress> seeds = new ArrayList<>();
			for (String server : servers.split(",")) {
				String[] split = server.split(":");
				seeds.add(new ServerAddress(split[0], Integer.parseInt(split[1])));
			}
			mongo = new MongoClient(seeds, options);
		} else {
			String[] split = servers.split(":");
			ServerAddress addr = new ServerAddress(split[0], Integer.parseInt(split[1]));
			mongo = new MongoClient(addr, options);
		}

		MongoDbFactory mongoDbFactory;

		if (!org.springframework.util.StringUtils.isEmpty(username)) {
			UserCredentials credentials = new UserCredentials(username, password);
			mongoDbFactory = new SimpleMongoDbFactory(mongo, dbName, credentials);
		} else {
			mongoDbFactory = new SimpleMongoDbFactory(mongo, dbName);
		}

		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);

		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		return new MongoTemplate(mongoDbFactory, converter);
	}
}
