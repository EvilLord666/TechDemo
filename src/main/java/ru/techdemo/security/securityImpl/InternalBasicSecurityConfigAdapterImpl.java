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
import ru.techdemo.dal.IApplicationDataContext;
import ru.techdemo.dal.InMemoryMockApplicationContext;
import ru.techdemo.dal.entity.UserEntity;


public class InternalBasicSecurityConfigAdapterImpl {
    
    public void configure(HttpSecurity http) throws Exception{
            http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
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
