package com.example.Barbora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarboraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarboraApplication.class, args);
	}

}
