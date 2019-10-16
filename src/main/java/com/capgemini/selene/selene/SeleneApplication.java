package com.capgemini.selene.selene;

import com.capgemini.selene.selene.engine.SeleneDataManager;
import com.capgemini.selene.selene.model.SeleneData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleneApplication {

	public static void main(String[] args) {

		// Prepare data
		SeleneDataManager.generateData();

		// Launch the spring API.
		SpringApplication.run(SeleneApplication.class, args);
	}

}
