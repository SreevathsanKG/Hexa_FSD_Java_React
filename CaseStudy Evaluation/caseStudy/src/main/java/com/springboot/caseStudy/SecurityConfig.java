package com.springboot.caseStudy;

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

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
					// Appointment
					.requestMatchers("/api/appointment/post/{patientId}/{doctorId}").permitAll()
					.requestMatchers("/api/appointment/get-by/{doctorId}").hasAuthority("DOCTOR")
					// Doctor
					.requestMatchers("/api/doctor/post").hasAuthority("ADMIN")
					.requestMatchers("/api/doctor/get-all").authenticated()
					// MedicalHistory
					.requestMatchers("/api/medicalHistory/post").hasAuthority("PATIENT")
					.requestMatchers("/api/medicalHistory/get-by/{patientId}").authenticated()
					// Patient
					.requestMatchers("/api/patient/post").permitAll()
					.requestMatchers("/api/patient/get-all").authenticated()
					// PatientLab
					.requestMatchers("/api/patientLab/get-by/{patientId}").permitAll()
					.anyRequest().authenticated()
			 )
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.httpBasic(Customizer.withDefaults());
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
