package ru.techdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@Order(1)
public class AppSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.requestMatchers()
          //.antMatchers("/login", "/oauth/authorize")
          //.and()
          //.authorizeRequests()
          //.anyRequest().authenticated()
          //.and()
          //.formLogin().permitAll();
          configureHttpBasicSecurity(http);
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
            .withUser("root")
            .password(passwordEncoder().encode("123"))
            .roles("USER");*/
        configureAuthBasicSecurity(auth);
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
        
    }
    
    private void configureAuthOauth2OpenAm(AuthenticationManagerBuilder auth) throws Exception {
        
    }
}
