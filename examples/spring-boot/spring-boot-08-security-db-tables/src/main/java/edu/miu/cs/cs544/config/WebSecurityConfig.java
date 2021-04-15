package edu.miu.cs.cs544.config;

import javax.sql.DataSource;

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
     
	private static final String USERNAME_QUERY = "SELECT e.[EmailAddress] AS username, p.PasswordHash AS [password], 1 AS [enabled]"
			+ "  FROM [AdventureWorks2019].[Person].[EmailAddress] e "
			+ "  INNER JOIN [AdventureWorks2019].[Person].[Password] p ON p.BusinessEntityID = e.BusinessEntityID"
			+ "  WHERE e.[EmailAddress] = ?";
	
	private static final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT e.EmailAddress AS username, 'ROLE_' + p.PersonType AS [role]"
			+ "  FROM [AdventureWorks2019].[Person].[Person] p"
			+ "  INNER JOIN [AdventureWorks2019].[Person].[EmailAddress] e ON e.BusinessEntityID = p.BusinessEntityID"
			+ "  WHERE e.EmailAddress =?";
 
    @Autowired
    private DataSource dataSource;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        auth.inMemoryAuthentication()
    		.passwordEncoder(encoder)
    		.withUser("service")
    		.password(encoder.encode("123"))
    		.roles("Service");
    	
        auth.jdbcAuthentication()        	
        	.passwordEncoder(encoder) 
            .dataSource(dataSource)
            .usersByUsernameQuery(USERNAME_QUERY)
            .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http		
		.authorizeRequests()
			.antMatchers("/favicon.ico").permitAll()
			.antMatchers("/countries/**").hasAnyRole("Service", "EM")
			.antMatchers("/people/**").hasRole("Service")
			.anyRequest().authenticated()
			.and()
		.formLogin();
    }
    
}

