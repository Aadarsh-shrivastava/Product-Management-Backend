package com.exittest.productsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductsearchApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProductsearchApplication.class, args);
	}
	
	 public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins(
	                        "http://localhost:4200"
	                )
	                .allowedMethods(
	                        "GET",
	                        "PUT",
	                        "POST",
	                        "DELETE",
	                        "PATCH",
	                        "OPTIONS"
	                );
	    }
}
