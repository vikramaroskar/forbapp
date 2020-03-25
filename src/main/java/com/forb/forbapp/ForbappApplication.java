package com.forb.forbapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main spring boot application class
 * 
 * @author Vikram Aroskar
 *
 */
@SpringBootApplication
@ComponentScan
public class ForbappApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(ForbappApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/place-order").allowedOrigins("*");
			}
		};
	}

	
}
