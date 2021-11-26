package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
		http.csrf().disable()
        .authorizeExchange().pathMatchers("/actuator/**")
           .authenticated().and().httpBasic();

           return http.build();
	}
	
	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		
		  UserDetails user1 = User
			      .withUsername("india")
			      .password(encoder.encode("india"))
			      .roles("ADMIN")
			           .build();
		  
		  UserDetails user2 = User
			      .withUsername("nepal")
			      .password(encoder.encode("nepal"))
			      .roles("USER")
			           .build();
			  
		return new MapReactiveUserDetailsService(user1,user2);
	}
}
