/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.security.authorizationServers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


public class InternalOAuth2ServerImpl {
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM);
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        clients.inMemory()
               .withClient(OAUTH2_RESOURCE_SERVER_USERNAME)
               .secret(passwordEncoder.encode(OAUTH2_RESOURCE_SERVER_PASSWORD))
               .authorizedGrantTypes("password", "authorization_code", "refresh_token")
               .authorities("READ_ONLY_CLIENT")
               .scopes("read", "write", "trust")
               .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
               .accessTokenValiditySeconds(5000)
               .refreshTokenValiditySeconds(50000);
    }
    
    private final String REALM = "INTERNAL_OAUTH2_REALM";
    private final String OAUTH2_RESOURCE_SERVER_USERNAME = "mjolnir";
    private final String OAUTH2_RESOURCE_SERVER_PASSWORD = "12345678";
}
