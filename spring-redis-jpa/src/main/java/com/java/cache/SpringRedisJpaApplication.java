package com.java.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisJpaApplication.class, args);
	}

}
