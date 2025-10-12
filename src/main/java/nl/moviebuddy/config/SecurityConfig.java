package nl.moviebuddy.config;

import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            JwtUtil jwt,
                                            UserRepository userRepo) throws Exception {

        var jwtFilter = new JwtAuthFilter(jwt, userRepo);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // H2-console
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                        .requestMatchers("/auth/**", "/genres/**", "/movies/**", "/h2/**").permitAll()

                        .requestMatchers("/users/*/watchlist/**", "/reviews/**").hasAuthority("ROLE_USER")

                        .requestMatchers("/uploads/**").hasAuthority("ROLE_ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
