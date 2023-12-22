package com.mera.colegio.etapa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectocolegioPieromApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectocolegioPieromApplication.class, args);
	}

}
