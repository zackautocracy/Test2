package com.sentrysoftware.Test2.processor.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/public/**")
                .build();
    }
    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("private")
                .packagesToScan("com.sentrysoftware.Test2.processor.controllers")
                .build();
    }
}


