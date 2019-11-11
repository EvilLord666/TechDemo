/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.client.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableOAuth2Sso
//@Configuration
public class AppSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    //@Override
    public void configure(HttpSecurity http) throws Exception { 
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/openid-connect/auth/**",
              "/login").permitAll()
            .anyRequest().authenticated().and()
            .logout().permitAll().logoutSuccessUrl("/");
    }
    
    //@Autowired
    //private ResourceServerTokenServices resourceServerTokenServices;
}
