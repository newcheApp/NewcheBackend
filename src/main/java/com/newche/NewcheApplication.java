package com.newche;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewcheApplication implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(NewcheApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(NewcheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("NEWCHE Backend Service is started!...");
		logger.info("Welcome!...");
	}

}
