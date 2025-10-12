package nl.moviebuddy.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Movie Buddy API",
                version = "v1",
                description = "REST API voor films, watchlist, reviews en uploads"))
@Configuration
public class OpenApiConfig {}
