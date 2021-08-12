package com.example.authenticationapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.List;

@Configuration
public class CorsConfig {

    /**
     * This function allows to filter origins URLs, only URLs from http://localhost:3000 are valid
     * @param allowedOrigins http://localhost:3000
     * @return instance of CorsFilter. Refer to https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/cors.html for more details
     */
    @Bean
    CorsFilter corsFilter(@Value("http://localhost:3000") List<String> allowedOrigins){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
