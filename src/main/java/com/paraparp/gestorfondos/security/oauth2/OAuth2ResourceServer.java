package com.paraparp.gestorfondos.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import static com.paraparp.gestorfondos.util.Consts.*;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("oauth2-resource");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				// Sirve para habilitar la consola de H2
				.antMatchers("/h2-console/**").permitAll().antMatchers(FUNDAPP_USERS).permitAll()
				.antMatchers(HttpMethod.GET, FUNDAPP_PORTFOLIOS, FUNDAPP_LOTS).hasRole(USER)
				.antMatchers(HttpMethod.POST, FUNDAPP_PORTFOLIOS, FUNDAPP_LOTS).hasAnyRole(USER, ADMIN)
				.antMatchers(HttpMethod.DELETE, FUNDAPP_PORTFOLIOS, FUNDAPP_LOTS).hasRole(USER)
				.antMatchers(HttpMethod.DELETE, FUNDAPP_SYMBOLS).hasRole(ADMIN)
				.antMatchers(HttpMethod.POST, FUNDAPP_USERS).permitAll().anyRequest().authenticated();

		// Sirve para habilitar la consola de H2
		http.headers().frameOptions().disable();
	}

}
