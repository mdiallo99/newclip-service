package com.example.authenticationapp.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class KeycloakAdminConfig {

    /**
     * For using keycloak, we have to create an account. We must login with that account before doing anything
     * So, this class allows to login our Keyloak as Admin
     */
    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;
    public static final String REALM_NAME= "master";
    public static final String CLIENT_ID = "admin-cli";
    private static final String USER_NAME = "mdiallo";
    private static final String PASS_WORD = "mdiallo";


    @Bean
    Keycloak keycloakAdmin(){
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(REALM_NAME)
                .username(USER_NAME)
                .password(PASS_WORD)
                .clientId(CLIENT_ID)
                .build();
    }

}
