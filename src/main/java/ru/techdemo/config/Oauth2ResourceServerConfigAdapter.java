/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/*
 *  Resource server is WHAT we protecting
 *
 *
 *
 *
 */

//@Configuration
//@EnableResourceServer
public class Oauth2ResourceServerConfigAdapter extends ResourceServerConfigurerAdapter {
    
   //@Override
    public void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests()
            //.antMatchers("/").authenticated();
    }
}
