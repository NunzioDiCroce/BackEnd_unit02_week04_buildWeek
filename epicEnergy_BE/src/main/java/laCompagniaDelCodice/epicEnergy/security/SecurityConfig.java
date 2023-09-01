package laCompagniaDelCodice.epicEnergy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JWTAuthFilter jwtFilter;
	@Autowired
	CorsFilter corsFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http.cors(c -> c.disable());
		http.csrf(c -> c.disable());

		// Se vogliamo utilizzare JWT dobbiamo disabilitare anche le sessioni
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		/* DA AGGIUNGERE GLI ENDPOINTS CHE VOGLIAMO AUTENTICARE */
		// http.authorizeHttpRequests(auth ->
		// auth.requestMatchers("/dispositivi/**").authenticated());

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/province/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/sedi/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/fatture/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/clienti/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/comuni/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/ruoli/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(corsFilter, JWTAuthFilter.class);

		return http.build();

	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
