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

            // 🔥 CSRF aus (REST API)
            .csrf(csrf -> csrf.disable())

            // 🔥 AUTH RULES
            .authorizeHttpRequests(auth -> auth

                // Preflight Requests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Public API (falls du später nutzt)
                .requestMatchers("/api/public/**").permitAll()

                // ✅ PUBLIC CHALLENGES (WICHTIG FIX)
                .requestMatchers("/api/challenge", "/api/challenge/**").permitAll()

                // 🔐 USER DATA (Auth0 geschützt)
                .requestMatchers("/api/user-challenges/**").authenticated()
                .requestMatchers("/api/profile").authenticated()

                // alles andere geschützt
                .anyRequest().authenticated()
            )

            // 🔐 JWT (Auth0)
            .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

        return http.build();
    }
}