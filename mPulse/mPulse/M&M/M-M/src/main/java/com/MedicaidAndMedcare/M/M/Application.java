package com.MedicaidAndMedcare.M.M;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {



	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}

	//Test
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);


	}

}
