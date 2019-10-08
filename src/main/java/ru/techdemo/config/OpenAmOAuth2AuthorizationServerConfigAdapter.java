/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * ********************************************************************************
 * The authorization server will be generating tokens for an API client
 *
 *
 *
 *********************************************************************************
 */


@Configuration
@EnableAuthorizationServer
public class OpenAmOAuth2AuthorizationServerConfigAdapter extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        /*security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();*/
        oauthServer.realm(DEV_REALM);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("mjolnir")
               .secret(passwordEncoder.encode("12345678"))
               .authorizedGrantTypes("password", "authorization_code", "refresh_token")
               .authorities("READ_ONLY_CLIENT")
               .scopes("\"read\", \"write\", \"trust\"")
               .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
               //.resourceIds("oauth2-resource")
               //.redirectUris("http://localhost:8081/login")
               .accessTokenValiditySeconds(5000)
               .refreshTokenValiditySeconds(50000);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
        .authenticationManager(authenticationManager);
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private UserApprovalHandler userApprovalHandler;
 
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    
    private final String DEV_REALM = "Simplest";
}
