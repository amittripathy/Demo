package com.example.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
	@Value("${spring.redis.port}")	
	int port;
	@Bean
	@Profile("default")
	JedisConnectionFactory jedisonnectionfactoryLocal() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost",port);
		//redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));
		JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
		jedisClientConfigurationBuilder.connectTimeout(Duration.ofSeconds(6));
		jedisClientConfigurationBuilder.readTimeout(Duration.ofSeconds(3));
		return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfigurationBuilder.build());
	}
	
	@Bean
	@Profile("default")
	public RedisTemplate<String, Object> redishTemplateLocal(){
		RedisTemplate<String, Object> temp = new RedisTemplate<String, Object> ();
		temp.setConnectionFactory(jedisonnectionfactoryLocal());
		return temp;
	}
}
