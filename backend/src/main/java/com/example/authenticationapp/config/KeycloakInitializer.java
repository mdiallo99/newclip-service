package com.example.authenticationapp.config;

import com.example.authenticationapp.model.user.User;
import com.example.authenticationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class KeycloakInitializer implements CommandLineRunner {

    /**
     * This class initialize our app data in Keycloak
     */

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    private final Keycloak keycloakAdmin;
    private static final String SERVICE_REAL_NAME = "newclip-services";
    private static final String CLIENT_ID = "authentication-app";
    private static final String REDIRECT_URL = "http://localhost:3000/*";

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing '{}' realm in Keycloak", SERVICE_REAL_NAME);
        /**
         * We find the realm name in keycloak. We remove it if it exists and create a new data
         */
        Optional<RealmRepresentation> realmRepresentational = keycloakAdmin.realms().findAll().stream()
                .filter(res -> res.getRealm().equals(SERVICE_REAL_NAME)).findAny();

        if(realmRepresentational.isPresent()){
            log.info("Removing already preconfigured '{}' realm", SERVICE_REAL_NAME);
            keycloakAdmin.realm(SERVICE_REAL_NAME).remove();
        }

        /**
         * Realm creation
         */
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(SERVICE_REAL_NAME);
        realmRepresentation.setEnabled(true);
        realmRepresentation.setRegistrationAllowed(true);
        realmRepresentation.setRememberMe(true);
//        realmRepresentation.setVerifyEmail(true);


        /**
         * Client creation
         */
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(CLIENT_ID);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setPublicClient(true);
        clientRepresentation.setRedirectUris(Collections.singletonList(REDIRECT_URL));

        /**
         * Bind the created client to the realm
         */
        realmRepresentation.setClients(Collections.singletonList(clientRepresentation));

        /**
         * We init keycloak users from database
         */
        List<User> APP_USERS = userRepository.findAll();

        /**
         * Each user has his credential (username, password, roles)
         */
        List<UserRepresentation> userRepresentations = APP_USERS.stream().map(user -> {
            Map<String, List<String>> clientRoles  = new HashMap<>();

            clientRoles.put(CLIENT_ID, new ArrayList<>(user.getRoles()));

            /**
             * User credentials
             */
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(user.getPassword());


            /**
             * User representation
             */
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(user.getEmail());
            userRepresentation.setEnabled(true);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
            userRepresentation.setClientRoles(clientRoles);

            return userRepresentation;

        }).collect(Collectors.toList());

        realmRepresentation.setUsers(userRepresentations);
        keycloakAdmin.realms().create(realmRepresentation);

        log.info("'{}' initialized successfully ! ", SERVICE_REAL_NAME);
    }

}