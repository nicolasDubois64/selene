package com.capgemini.selene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.selene.engine.SeleneDataManager;

@SpringBootApplication
public class SeleneApplication {

	// Please put this at false when deploying ;)
	public static boolean DEBUG = true;

	public static void main(String[] args) {
		// Prepare data
		SeleneDataManager.generateData();

		// Launch the spring API.
		SpringApplication.run(SeleneApplication.class, args);
	}

}
