package com.api.ratelimiter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rateLimiterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rate Limiter API")
                        .version("1.0")
                        .description(
                                "Production-inspired distributed rate limiting microservice " +
                                        "built with Spring Boot and Redis."
                        ));
    }
}