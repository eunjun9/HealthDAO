package com.kh.healthDao.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kh.healthDao")
public class HealthDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthDaoApplication.class, args);
	}

}
