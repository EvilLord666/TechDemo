/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.security.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.techdemo.security.client.OpenAmOAuth2Client;
import ru.techdemo.security.services.RestOAuth2UserService;

//@EnableOAuth2Sso
@Configuration
public class AppSecurityExtServiceConfigAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http
            //.oauth2Login()
                //.tokenEndpoint().accessTokenResponseClient(new OpenAmOAuth2Client(restOperations()));
                //.and()
                //.userInfoEndpoint().userService(new RestOAuth2UserService(restOperations()));
                
        // From the root '/' down...
        /*
                http.antMatcher("/**")
                // requests are authorised...
                .authorizeRequests()
                // ...to these url's...
                .antMatchers("/", "/login**", "/debug/**", "/webjars/**", "/error**")
                // ...without security being applied...
                .permitAll()
                // ...any other requests...
                .anyRequest()
                // ...the user must be authenticated.
                .authenticated()
                .and()
                .formLogin().disable()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .csrf();*/
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    /*@Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }*/
    
    /*@Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
         return null;
    }*/
 
}
