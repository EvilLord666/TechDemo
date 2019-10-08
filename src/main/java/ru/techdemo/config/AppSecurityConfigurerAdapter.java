package ru.techdemo.config;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
// import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/*
 *
 *   AuthenticationManager: Authenticates the request
 *   TokenStore: Stores the OAuth2 tokens in memory
 *   TokenStoreUserApprovalHandler: Remembers the approval decisions by consulting existing tokens
 *   TokenApprovalStore: An ApprovalStore that works with an existing TokenStore
 *
 */

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
        configureHttpOauth2OpenAmSecurity(http);
        //configureHttpBasicSecurity(http);
    }
 
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //configureAuthBasicSecurity(auth);
    }*/
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
    
    private void configureHttpBasicSecurity(HttpSecurity http) throws Exception{
        http.csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .httpBasic();
    }
    
    private void configureAuthBasicSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("123"))
            .roles("USER");
    }
    
    private void configureHttpOauth2OpenAmSecurity(HttpSecurity http) throws Exception{
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
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .realmName("SIMPLEST");
    }
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("admin").password("123").roles("ADMIN","USER").and()
        .withUser("root").password("123").roles("ADMIN","USER").and()
        .withUser("michael").password("1234").roles("USER");
    }
    
    @Autowired
    private ClientDetailsService clientDetailsService;
}
