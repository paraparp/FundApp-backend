package com.paraparp.gestorfondos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestorFondosBackendApplication

{

	public static void main(String[] args) {
		SpringApplication.run(GestorFondosBackendApplication.class, args);
	}

}
