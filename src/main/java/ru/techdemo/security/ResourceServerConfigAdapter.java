/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/*
 *  Resource server is Entity that Access AuthorizationServer and 
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfigAdapter extends ResourceServerConfigurerAdapter {
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**",
              "/login").permitAll()
            .anyRequest().authenticated().and()
            .logout().permitAll().logoutSuccessUrl("/");
    }
    
    /*public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
            .requestMatchers().antMatchers("/api/**")
            .and().authorizeRequests()
            .antMatchers("/api/**").access("hasRole('ADMIN') or hasRole('USER')")
            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }*/
    
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;
}
