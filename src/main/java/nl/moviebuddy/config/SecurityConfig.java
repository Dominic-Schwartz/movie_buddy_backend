package nl.moviebuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2/**").permitAll()   // H2 console toegankelijk
                        .anyRequest().permitAll()
                )
                .headers(h -> h.frameOptions(f -> f.disable())) // frames toestaan voor H2 console
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

