package com.rivera.colegio.apoderado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectocolegioriveraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectocolegioriveraApplication.class, args);
	}

}
