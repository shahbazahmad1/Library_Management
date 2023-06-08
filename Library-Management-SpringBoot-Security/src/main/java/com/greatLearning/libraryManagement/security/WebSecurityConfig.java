package com.greatLearning.libraryManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.libraryManagement.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsServiceImpl getuserUserDetailsServiceImpl() {
		UserDetailsServiceImpl impl = new UserDetailsServiceImpl();
		return impl;
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider authBuilder = new DaoAuthenticationProvider();
        authBuilder.setUserDetailsService(getuserUserDetailsServiceImpl());
        authBuilder.setPasswordEncoder(passwordEncoder());
        return authBuilder;
    }

	@Override
	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(getDaoAuthenticationProvider());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin();
		http.authorizeRequests()
    			.antMatchers("/", "/books/save", "/books/showFormForAdd", "/books/403")
				.hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/books/showFormForUpdate", "/books/delete")
				.hasAuthority("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list")
				.permitAll()
				.and().logout().logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling()
				.accessDeniedPage("/books/403").and().cors().and().csrf().disable();
	}

}
