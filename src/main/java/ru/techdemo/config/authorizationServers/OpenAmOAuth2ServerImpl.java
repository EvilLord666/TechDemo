/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.config.authorizationServers;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

public class OpenAmOAuth2ServerImpl {
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)  throws Exception {
    }
    
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
    }
}
