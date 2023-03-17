//package com.flightApplication.gatway.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
//import com.flightApplication.gatway.filter.JWTRequestFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//		@Autowired
//		JWTAuthenticationEntryPoint authenticationEntryPoint;
//		
//		@Autowired
//		JWTUserDetailsService jwtUserDetailsService;
//		
//		@Autowired
//		JWTRequestFilter requestFilter;
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			System.out.println("coming here");
//			http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
//			http
//			.csrf().disable()
//			.authorizeRequests().antMatchers("/api/v1.0/flight/**","/api/admin/**","/api/customer/**","/api/gatway/authenticate").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().disable();
//		}
//		
////		private CsrfTokenRepository csrfTokenRepository() 
////		{ 
////		    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
////		    repository.setSessionAttributeName("_csrf");
////		    return repository; 
////		}
//		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//					auth.userDetailsService(jwtUserDetailsService);
//		}
//		
//		@Bean
//		public PasswordEncoder passwordEncoder() {
//			return NoOpPasswordEncoder.getInstance();
//		}
//		
//		@Bean
//		@Override
//		public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//		}
//}
