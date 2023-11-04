package com.parking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.parking.constant.RoleConstant;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	@Autowired
	private  JwtAuthenticationFilter jwtAuthFilter;
	
	
	private AuthenticationProvider authenticationProvider  = new AuthenticationProvider() {
		
		@Override
		public boolean supports(Class<?> authentication) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.cors(Customizer.withDefaults()).build();
	}
	
	@Bean
	public SecurityFilterChain sFilterChain(HttpSecurity http) throws Exception
	{
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
				.antMatchers("/api/auth/**", "/ws/**")
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
