package com.example.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import redis.clients.jedis.Jedis;

import redis.embedded.RedisServer;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringRadisApplication {
private RedisServer redisServer;
	//Jedis redisServer;
	@Value("${spring.redis.port}")	
	int port;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringRadisApplication.class, args);
	}
	
	@PostConstruct
	private void started() {
		this.redisServer = new RedisServer(port);
		redisServer.start();
	}
	
	 @PreDestroy
	    public void preDestroy() {
	        redisServer.stop();
	    }

}
