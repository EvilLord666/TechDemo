package ru.techdemo.security.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import ru.techdemo.security.securityImpl.InternalOAuth2ConfigAdapterImpl;

/*
 *   AuthenticationManager: Authenticates the request
 *   TokenStore: Stores the OAuth2 tokens in memory
 *   TokenStoreUserApprovalHandler: Remembers the approval decisions by consulting existing tokens
 *   TokenApprovalStore: An ApprovalStore that works with an existing TokenStore
 *   THIS IS SECURITY CONFIG v1 THAT IS USING WITH INTERNAL OAUTH2 Authorization Server
 *   TO ENABLE SWITCH (UNCOMMENT)
 */


//@Configuration
//@EnableWebSecurity
public class AppSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    public AppSecurityConfigurerAdapter(){
        System.out.println("AppSecurityConfigurerAdapter INSTANTIATION.");
    }
    
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
        // v.1 UMV: usage impl of ConfigurerAdapter
        securityConfigAdapter.configure(http);
    }
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        securityConfigAdapter.configure(auth);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
 
    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }
    
    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    private final InternalOAuth2ConfigAdapterImpl securityConfigAdapter = new InternalOAuth2ConfigAdapterImpl();
}
