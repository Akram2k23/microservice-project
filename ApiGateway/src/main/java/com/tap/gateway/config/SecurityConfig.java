package com.tap.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
public class SecurityConfig {   // This class use to configure spring security in webFlux
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
		
		httpSecurity.authorizeExchange()
					.anyExchange()
					.authenticated()
					.and()
					.oauth2Client()
					.and()
					.oauth2ResourceServer()
					.jwt();
		
		return httpSecurity.build();
		
	}
}
