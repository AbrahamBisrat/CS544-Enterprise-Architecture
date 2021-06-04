package edu.miu.cs.cs544.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	
        auth.inMemoryAuthentication()
        	.passwordEncoder(encoder)
        	.withUser("service")
        	.password(encoder.encode("123"))
        	.roles("Service");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http		
		.authorizeRequests()
			.antMatchers("/favicon.ico").permitAll()
			.anyRequest().hasRole("Service")
			.and()
			.formLogin();
//			.and()
//		.csrf()
//			.disable()
//		.httpBasic();
    }
    
}

