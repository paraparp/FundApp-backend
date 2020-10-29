package com.paraparp.gestorfondos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import static  com.paraparp.gestorfondos.util.Consts.*;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@Order(1)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;



	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
		   .cors().and().csrf().disable()
	          .requestMatchers()
	          .antMatchers("/login", "/oauth/authorize")
	          .and()
	          .authorizeRequests()
	          
	         // .antMatchers(HttpMethod.POST, "/fundapp/users/register").permitAll()
	          .antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
	          .anyRequest().authenticated()
	          .and()
	          .formLogin().permitAll();
		
		
		
//		.cors().and().csrf().disable()
//		.requestMatchers().antMatchers("/login", "oauth/authorize" ).antMatchers("/fundapp/**").antMatchers("/oauth/**")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/**").permitAll()
//		.antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
////		.anyRequest().authenticated()
//		.and()
//		.formLogin().permitAll();
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//		        .requestMatchers().antMatchers("/login", "oauth/authorize" )
//				.and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
//				.antMatchers(HttpMethod.POST, FUNDAPP_USERS).permitAll()
//				.antMatchers(HttpMethod.GET, FUNDAPP_USERS).permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin().permitAll();

	}
	
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
