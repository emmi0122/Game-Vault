package se.yrgo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import se.yrgo.data.UserRepository;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

	// @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	// Exception {
	// http
	// .authorizeHttpRequests(authorize -> authorize
	// .requestMatchers("/register", "/login", "/h2-console/**").permitAll()
	// .anyRequest().authenticated())
	// .formLogin(withDefaults())
	// .httpBasic(withDefaults());

	// // Enable CSRF protection, but specifically ignore it for the H2 console
	// http.csrf(csrf -> csrf
	// .ignoringRequestMatchers("/h2-console/") // Ignore CSRF for H2 console only
	// );

	// // Allow the H2 console to be displayed in a frame
	// http.headers(headers -> headers
	// .frameOptions(frameOptions -> frameOptions.sameOrigin()));

	// return http.build();
	// }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(withDefaults())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/user/register", "/user/login", "/profile/getProfile", "/h2-console/**").permitAll()
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable()) // CSRF off för Insomnia
				.headers(headers -> headers.frameOptions().disable()) // H2 console
				.formLogin(form -> form.disable())
				.httpBasic(withDefaults())
				.build();
	}

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);                // Tillåter cookies/sessioner
        config.addAllowedOrigin("http://localhost:5173"); // Din frontend
        config.addAllowedHeader("*");                    // Tillåt alla headers (Content-Type, Authorization etc.)
        config.addAllowedMethod("*");                    // GET, POST, OPTIONS etc.

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


	// Database-backed users
	// @Bean
	// public UserDetailsService userDetailsService(UserRepository userRepository) {
	// return email -> userRepository.findUserByEmail(email)
	// .map(user -> {
	// List<String> roles = user.getUserProfile().getRoles();
	// String[] rolesArray = roles.toArray(new String[0]);

	// return org.springframework.security.core.userdetails.User
	// .withUsername(user.getEmail())
	// .password(user.getPassword())
	// .roles(rolesArray)
	// .build();
	// })
	// .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	// }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// UserDetails user = User.builder()
	// .username()
	// .password(user.getPassword()) // In production, use a secure password encoder
	// .roles("USER")
	// .build();

	// return new InMemoryUserDetailsManager(user);
	// }

}
