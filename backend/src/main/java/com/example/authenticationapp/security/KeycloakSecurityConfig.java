package com.example.authenticationapp.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import static com.example.authenticationapp.utils.Constants.*;

/**
 * This class handle Keycloak security configuration for our app
 */
@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        super.configure(httpSecurity);

        httpSecurity.authorizeRequests()
                    .antMatchers(HttpMethod.GET, USER_LIST_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.GET, COMPANY_LIST_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.GET, GET_COMPANY_BY_CODE_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.GET, ADDRESS_LIST_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.POST, ADD_ADDRESS_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.POST, ADD_COMPANY_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.POST, ADD_USER_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.DELETE, DELETE_ADDRESS_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.GET, FIND_DELETE_ADDRESS_ENDPOINT).hasAnyRole(ADMIN)
    //                .antMatchers(HttpMethod.DELETE, FIND_DELETE_ADDRESS_ENDPOINT).hasAnyRole(ADMIN)
                    .antMatchers(HttpMethod.GET, USER_PROFILE_ENDPOINT).hasAnyRole(ADMIN,USER)
                    .antMatchers(HttpMethod.GET, SYLOB_BACKEND_ENDPOINT).hasAnyRole(ADMIN, USER)
                    .antMatchers(HttpMethod.GET, SYLOB_DATA_ENDPOINT).hasAnyRole(ADMIN,USER)
                    .antMatchers(HttpMethod.GET, SYLOB_COMPANY_LIST_ENDPOINT).hasAnyRole(ADMIN,USER)
                    .antMatchers(HttpMethod.POST, ADD_SYLOB_ARTICLE_ENDPOINT).hasAnyRole(ADMIN, USER)
                    .antMatchers(HttpMethod.GET, SYLOB_KIT_LIST_ENDPOINT).hasAnyRole(ADMIN,USER)
                    .antMatchers(HttpMethod.GET, FIND_KIT_BY_LABEL_ENDPOINT).hasAnyRole(ADMIN, USER)
                    .antMatchers(HttpMethod.GET, DELETE_SYLOB_ARTICLE_ENDPOINT).hasAnyRole(ADMIN, USER)
                    .antMatchers(HttpMethod.POST, ADD_IMAGE_ENDPOINT).hasAnyRole(ADMIN, USER)
                    .antMatchers(HttpMethod.GET, SWAGGER_CONFIG_URL, SWAGGER_UI_URL, SWAGGER_DOC_URL, SWAGGER_DOCS_URL).permitAll()
                    .anyRequest().authenticated();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.cors().and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager(){
        return new HttpSessionManager();
    }

}
