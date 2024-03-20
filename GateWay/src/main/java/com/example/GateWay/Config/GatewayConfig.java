package com.example.GateWay.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/v1/users/**")
                        .uri("lb://SPRING-CLOUD-EUREKA-USER"))

                .route(r->r
                        .path("/api/v1/tasks/**")
                        .uri("lb://SPRING-CLOUD-EUREKA-TASK"))
                .build();

    }



    /*@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("SPRING-CLOUD-EUREKA-USERSERVICE", r -> r
                        .path("/users/**")
                        .uri("http://localhost:8081/"))
                .route("SPRING-CLOUD-EUREKA-TASKSERVICE", r -> r
                        .path("/tasks/**")
                        .uri("lb://SPRING-CLOUD-EUREKA-USERSERVICE"))
                .build();
    }*/
}
