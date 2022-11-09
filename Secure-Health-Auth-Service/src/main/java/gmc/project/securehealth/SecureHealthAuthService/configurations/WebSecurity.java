package gmc.project.securehealth.SecureHealthAuthService.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import gmc.project.securehealth.SecureHealthAuthService.filters.AuthenticationFilter;
import gmc.project.securehealth.SecureHealthAuthService.services.AuthService;

@Configuration
public class WebSecurity {
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private AuthService authService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
		authenticationManager = auth.build();
		
		http
			.authorizeRequests().anyRequest().permitAll()
		.and()
			.headers().frameOptions().disable()
		.and()
			.csrf().disable();
		http.addFilter(getAuthenticationFilter());
		http.authenticationManager(authenticationManager);
		
		return http.build();
	}
	
	public AuthenticationFilter getAuthenticationFilter() {
		AuthenticationFilter authFilter = new AuthenticationFilter(authService, authConfig, authenticationManager);
		authFilter.setFilterProcessesUrl(authConfig.getAuthUrl());
		return authFilter;
	}

}
