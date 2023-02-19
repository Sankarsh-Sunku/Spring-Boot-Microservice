package com.bajaj.apiGateway.config;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ReddisConfig {
	
	private Environment env;
	
	public ReddisConfig(Environment env) {
		this.env = env;
	}
	
	@Bean
	//JedisConnectionFactory is a class in the 
	//Spring Data Redis library that provides a connection factory for Redis using the Jedis client.
	//Jedis is a popular Java Redis client library that allows you to interact with Redis in a Java application.
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                Objects.requireNonNull(env.getProperty("spring.redis.host")),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.redis.port"))));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    //RedisTemplate is a class in the Spring Data Redis library that provides
    //a high-level API for interacting with Redis using Java objects. 
    //Redis is an in-memory data structure store, often used as a database, cache, and message broker.
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        return template;
    }
}
