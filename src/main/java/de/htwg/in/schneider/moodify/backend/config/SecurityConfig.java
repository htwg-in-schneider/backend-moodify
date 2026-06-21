package de.htwg.in.schneider.moodify.backend.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // PUBLIC APIs
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/affirmations/**").permitAll()
                .requestMatchers("/api/moodquiz/**").permitAll()

                // AUTH REQUIRED
                .requestMatchers("/api/profile").authenticated()

                // EVERYTHING ELSE
                .anyRequest().authenticated()
            )

            .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

        return http.build();
    }
}
