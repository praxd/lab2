package com.unifil.br.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.unifil.br.lab01.entity")
@EnableJpaRepositories("com.unifil.br.lab01.repository")
@ComponentScan("com.unifil.br.resource") // mapeamento dos controllers

public class WebApp {

	public static void main(String[] args) {		
		SpringApplication.run(WebApp.class, args);
	}

}
