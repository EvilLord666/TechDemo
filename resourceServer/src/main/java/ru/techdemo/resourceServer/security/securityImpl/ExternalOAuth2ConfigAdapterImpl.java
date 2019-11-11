/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.security.securityImpl;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class ExternalOAuth2ConfigAdapterImpl {
    
    public void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();
    }
    
    
}
