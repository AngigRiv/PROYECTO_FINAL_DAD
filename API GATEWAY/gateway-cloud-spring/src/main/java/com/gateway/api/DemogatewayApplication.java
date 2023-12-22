package com.gateway.api;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemogatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
        		// microservices ballena ruiz
            .route("docente_route", r -> r.path("/api/docente/**")
                .uri("https://4ec0-179-7-80-38.ngrok-free.app"))
         // microservices rivera cartagena 
            .route("alumno_route", r -> r.path("/api/apoderado/**")
                .uri("https://c8af-38-56-218-196.ngrok-free.app"))
         // microservices panta yanovich
            .route("aula_route", r -> r.path("/api/aula/**")
                .uri("https://e8cb-38-56-218-236.ngrok-free.app"))
         // microservices guerrero herrera
            .route("alumno_route", r -> r.path("/api/alumno/**")
                .uri("https://ec7f-2803-a3e0-1514-1190-a55c-40bf-2eab-2d62.ngrok-free.app"))
            // microservices mera burga 
            .route("etapa_route", r -> r.path("/api/etapa/**")
                .uri("https://0115-179-51-160-7.ngrok-free.app"))
     
                        
            .build();
    }
}


