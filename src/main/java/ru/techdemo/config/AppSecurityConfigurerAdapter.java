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
// import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
//@Order(1)
public class AppSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureHttpOauth2OpenAmSecurity(http);
        //configureHttpBasicSecurity(http);
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //configureAuthBasicSecurity(auth);
    }
     
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
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
                .authenticated().and().exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login/openam")).and().logout()
                .logoutSuccessUrl("/").permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }
    
    private void configureAuthOauth2OpenAm(AuthenticationManagerBuilder auth) throws Exception {
        
    }
    
    @Bean
    @ConfigurationProperties("openam.client")
    public AuthorizationCodeResourceDetails openAm() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("openam.resource")
    public ResourceServerProperties openAmResource() {
        return new ResourceServerProperties();
    }
    
    @Bean
    private FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter openAMFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/openam");
        OAuth2RestTemplate openAMTemplate = new OAuth2RestTemplate(openAm(), this.oauth2ClientContext);
        openAMFilter.setRestTemplate(openAMTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(openAmResource().getUserInfoUri(), openAm().getClientId());
        tokenServices.setRestTemplate(openAMTemplate);
        openAMFilter.setTokenServices(new UserInfoTokenServices(openAmResource().getUserInfoUri(), openAm().getClientId()));
        return openAMFilter;
    }
    
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;
}
