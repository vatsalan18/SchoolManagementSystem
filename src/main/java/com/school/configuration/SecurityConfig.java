package com.school.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
		 .csrf(csrf -> csrf
			       .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			       .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
			       .ignoringRequestMatchers(new AntPathRequestMatcher("/logout"))
			 )
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/two-factor", "/login","/auth/login","/auth/verify").permitAll() // Public endpoints
             .anyRequest().authenticated()
         )
        .formLogin(form -> form .loginPage("/login")
		.defaultSuccessUrl("/two-factor", true) .permitAll() 
		)	 
        .logout(logout -> logout
             .logoutUrl("/logout")
             .logoutSuccessUrl("/login")
             .invalidateHttpSession(true)
             .deleteCookies("JSESSIONID")
         )
         .sessionManagement(session -> session
                 .maximumSessions(1)  // Allow only one active session
                 .expiredUrl("/login?expired=true")  // Redirect to login page if session expired
                 .maxSessionsPreventsLogin(true)
             );
		 return http.build();
    }
	
	 @Bean 
	  public BCryptPasswordEncoder passwordEncoder() { return new
	  BCryptPasswordEncoder(); }
	 
	 @Bean
	 public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	     return authenticationConfiguration.getAuthenticationManager();
	 }
	 
}
