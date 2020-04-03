package com.paraparp.gestorfondos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/fundapp/users").permitAll()
//		.antMatchers(HttpMethod.GET, "/fundapp/users/{id}").hasAnyRole("USER","ADMIN")
//		.antMatchers(HttpMethod.POST, "/fundapp/users").hasRole("ADMIN")
//		.antMatchers( "/fundapp/users/**").hasRole("ADMIN")
		.anyRequest().authenticated();
	}


}