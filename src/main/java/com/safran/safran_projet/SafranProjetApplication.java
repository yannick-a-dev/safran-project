package com.safran.safran_projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SafranProjetApplication {
	public static void main(String[] args) {
		SpringApplication.run(SafranProjetApplication.class, args);
	}

}
