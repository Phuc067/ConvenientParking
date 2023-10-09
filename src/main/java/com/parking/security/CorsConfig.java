package com.parking.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Value("${allowed.origin}")
	private String allowedOrigin;
    @Bean
    WebMvcConfigurer webMvcConfigurer()
	{
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings( CorsRegistry registry)
			{
				registry.addMapping("/**")
						.allowedOrigins(allowedOrigin)
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowCredentials(true);
			}
		};
	}
	
}


