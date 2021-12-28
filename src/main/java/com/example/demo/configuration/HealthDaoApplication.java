package com.example.demo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class HealthDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthDaoApplication.class, args);
	}

}
