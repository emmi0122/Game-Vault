package se.yrgo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import se.yrgo.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// Password encoder for database users
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Security filter chain
	// @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http,
	// UserDetailsService userDetailsService)
	// throws Exception {
	// return http.authorizeHttpRequests(authorize -> authorize
	// .requestMatchers("/csrf", "/register", "/login", "/h2-console/**")
	// .permitAll()
	// .anyRequest().authenticated())
	// .csrf(csrf -> csrf
	// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	// .ignoringRequestMatchers("/csrf", "/h2-console/**", "/register", "/login"))
	// .headers(headers -> headers.frameOptions().disable())
	// // .httpBasic(withDefaults())
	// .httpBasic(basic -> basic.disable())
	// .formLogin(form -> form.disable())
	// .userDetailsService(userDetailsService)
	// .build();
	// }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/register", "/login", "/h2-console/**").permitAll()
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable()) // CSRF off för Insomnia
				.headers(headers -> headers.frameOptions().disable()) // H2 console
				.httpBasic(basic -> basic.disable())
				.formLogin(form -> form.disable())
				.build();
	}

	// Database-backed users
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return email -> userRepository.findUserByEmail(email)
				.map(user -> {
					List<String> roles = user.getUserProfile().getRoles();
					String[] rolesArray = roles.toArray(new String[0]);

					return org.springframework.security.core.userdetails.User
							.withUsername(user.getEmail())
							.password(user.getPassword())
							.roles(rolesArray)
							.build();
				})
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
