package nl.moviebuddy.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthLoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
