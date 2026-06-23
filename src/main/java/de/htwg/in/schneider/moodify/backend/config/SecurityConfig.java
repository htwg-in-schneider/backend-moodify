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

                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/moodquiz/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/challenge/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/affirmations/**").permitAll()
                .requestMatchers("/api/profile/**").authenticated()
                .requestMatchers("/api/users/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/challenge/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/challenge/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/challenge/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/affirmations/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/affirmations/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/affirmations/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/moodquiz/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/moodquiz/**").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/moodquiz/**").authenticated()
                .requestMatchers("/api/mood/**").authenticated()
                .requestMatchers("/api/visionboard/**").authenticated()
                .requestMatchers("/api/image/**").authenticated()

    
    .anyRequest().authenticated()

            )

            .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

        return http.build();
    }
}
