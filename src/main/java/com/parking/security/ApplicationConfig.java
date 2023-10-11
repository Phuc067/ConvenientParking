package com.parking.security;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.parking.repository.LoginRepository;

import lombok.RequiredArgsConstructor; 

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class ApplicationConfig {
	
	@Autowired
	private LoginRepository loginRepository;
	
	 @Bean
	 public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	       	JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);
	        return transactionManager;
	}

    @Bean
    UserDetailsService userDetailsService() throws UsernameNotFoundException{
		return  username -> loginRepository.findByUsername(username);
	}
    
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsService());
    	authenticationProvider.setPasswordEncoder(passwordEncoder());
    	return authenticationProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
    	return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
}
