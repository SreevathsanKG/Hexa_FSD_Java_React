package com.springboot.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration			// --> this ensures that this class gets called during every API call
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf((csrf) -> csrf.disable())					// -> makes to post
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/user/token").permitAll()
				.requestMatchers("/api/user/details").permitAll()
				.requestMatchers("/api/learner/get-all").permitAll()
				.requestMatchers("/api/learner/add").permitAll()
				.requestMatchers("/api/learner/get-one").hasAuthority("LEARNER")
				.requestMatchers("/api/course/add").hasAuthority("AUTHOR")
				.requestMatchers("/api/course/get/all").permitAll()
				.requestMatchers("/api/course/get-by/author").permitAll()
				.requestMatchers("/api/course/get-by/id/{id}").permitAll()
				.requestMatchers("/api/author/post").permitAll()
				.requestMatchers("/api/author/upload/profile-pic").permitAll()
				.requestMatchers("/api/author/get").permitAll()
				.requestMatchers("/api/author/enroll/stats").permitAll()
				.requestMatchers("/api/module/add/{courseId}").hasAuthority("AUTHOR")
				.requestMatchers("/api/video/add/{moduleId}").hasAuthority("AUTHOR")
				.requestMatchers("/api/video/get-by/courseId/{courseId}").permitAll()
				.requestMatchers("/api/learner/enroll/course/{learnerId}/{courseId}").permitAll()
				.requestMatchers("/api/learner/course/{courseId}").permitAll()
				.requestMatchers("/api/review/add/{learnerId}/{courseId}").authenticated()
				.requestMatchers("/api/review/get-by/courseId/{courseId}").permitAll()
				.anyRequest().authenticated()
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
			.httpBasic(Customizer.withDefaults());		// -> this activated http basic technique

		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
}
