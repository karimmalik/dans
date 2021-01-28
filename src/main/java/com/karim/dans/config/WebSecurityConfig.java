package com.karim.dans.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.karim.dans.service.CustomUserDetailsService;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Override
//	protected void configure (HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.csrf().disable()
//		.authorizeRequests().anyRequest().authenticated()
//		.and().httpBasic();
//	}
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
//		builder.inMemoryAuthentication().withUser("admin")
//		.password("12345")
//		.roles("USER");
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/user").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.usernameParameter("username")
				.defaultSuccessUrl("/list")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/login_form").permitAll();
	}
}





















