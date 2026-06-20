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
            
            .cors(withDefaults())

            
            .csrf(csrf -> csrf.disable())

            
            .authorizeHttpRequests(auth -> auth

                
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/challenge/*").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/challenge/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/challenge/*").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/challenge/*").permitAll()

                
                .requestMatchers("/api/public/**").permitAll()

                
                .requestMatchers("/api/challenge", "/api/challenge/**").permitAll()

                
                .requestMatchers("/api/user-challenges/**").authenticated()
                .requestMatchers("/api/profile").authenticated()

                
                .anyRequest().authenticated()
            )

            .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

        return http.build();
    }
}