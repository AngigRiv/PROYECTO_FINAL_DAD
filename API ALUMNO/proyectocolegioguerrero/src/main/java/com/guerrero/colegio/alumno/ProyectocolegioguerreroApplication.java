package com.guerrero.colegio.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectocolegioguerreroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectocolegioguerreroApplication.class, args);
	}

}
