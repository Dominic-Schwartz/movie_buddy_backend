package nl.moviebuddy.config;

import nl.moviebuddy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtUtil jwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expirationMillis}") long exp) {
        return new JwtUtil(secret, exp);
    }
}
