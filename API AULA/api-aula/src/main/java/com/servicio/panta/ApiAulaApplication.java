package com.servicio.panta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiAulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAulaApplication.class, args);
	}

}
