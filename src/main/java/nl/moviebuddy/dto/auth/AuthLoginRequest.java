package nl.moviebuddy.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
