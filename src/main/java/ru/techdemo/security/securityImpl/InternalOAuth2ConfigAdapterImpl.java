/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.security.securityImpl;

import java.util.List;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.techdemo.dal.IApplicationDataContext;
import ru.techdemo.dal.InMemoryMockApplicationContext;
import ru.techdemo.dal.entity.UserEntity;

public class InternalOAuth2ConfigAdapterImpl {
    
    public void configure(HttpSecurity http) throws Exception{
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/about").permitAll() 
            .antMatchers("/signup").permitAll()
            .antMatchers("/oauth/token").permitAll()
            .antMatchers("/api/**").authenticated()
            //.antMatchers("/api/**").hasRole("USER")
            //.anyRequest().authenticated()
            .and()
            .httpBasic()
            .realmName("SIMPLEST");
    }
    
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> details = auth.inMemoryAuthentication();
        List<UserEntity> users = context.getUsersRepository().getAll();
        users.forEach((user) -> {
            details.withUser(user.getUserName()).password(user.getPasswordHash()).roles(user.getRolesRepresentation());
        });
    }
    
    private final IApplicationDataContext context = new InMemoryMockApplicationContext();
}
