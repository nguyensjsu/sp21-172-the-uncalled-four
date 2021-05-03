package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class StarbucksApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarbucksApplication.class, args);
	}

}
