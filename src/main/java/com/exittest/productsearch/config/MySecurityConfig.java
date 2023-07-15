package com.exittest.productsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.exittest.productsearch.service.*;
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomeUserDetailService customeUserDetailService;
	
	@Autowired
	private JWTAuthenticationFilter jWTAuthenticationFilter;
	
	
//	  public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**");
//	    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
			.csrf().disable()
			.cors().and()
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.antMatchers("/users/**").permitAll()
			.antMatchers("/products/getProductsCount").permitAll()
			.antMatchers("/reviews/getReviewsCount").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jWTAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("aadarsh").password("123").roles("admin");
		auth.userDetailsService(customeUserDetailService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
		
	}
	
}
