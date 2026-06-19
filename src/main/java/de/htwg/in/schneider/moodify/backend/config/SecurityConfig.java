package de.htwg.in.schneider.moodify.backend.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            // 🔥 CORS aktivieren
            .cors(withDefaults())

            // 🔥 CSRF deaktivieren (für REST APIs korrekt)
            .csrf(csrf -> csrf.disable())

            // 🔥 Authorization Rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/challenge/**").authenticated()
                .requestMatchers("/api/user-challenges/**").authenticated()
                .requestMatchers("/api/profile").authenticated()
                .anyRequest().authenticated()
            )

            // 🔐 JWT Resource Server (Auth0)
            .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

        return http.build();
    }
}