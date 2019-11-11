/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.resourceServer.security;

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
import ru.techdemo.resourceServer.security.authorizationServers.InternalOAuth2ServerImpl;

/**
 * ********************************************************************************
 * The authorization server will be generating tokens for an API client
 *   There are a couple of implementation of Authorization Server
 *       1) Internal OAuth2 Server - InternalOAuth2Server
 *       2) OpenAm OAuth2 Server - OpenAmOAuth2ServerImpl
 * 
 *********************************************************************************
 */

//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfigAdapter extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        serverImpl.configure(oauthServer);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        serverImpl.configure(clients);
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
    
    
    private final InternalOAuth2ServerImpl serverImpl = new InternalOAuth2ServerImpl();
}
