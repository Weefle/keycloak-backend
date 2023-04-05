package com.tutorial.keycloakbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class KeycloakBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakBackendApplication.class, args);
	}

}
