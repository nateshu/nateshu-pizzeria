package com.nateshu.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class NateshuPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NateshuPizzeriaApplication.class, args);
	}

}
