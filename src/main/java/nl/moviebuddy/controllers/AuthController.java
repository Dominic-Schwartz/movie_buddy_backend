package nl.moviebuddy.controllers;

import jakarta.validation.Valid;
import nl.moviebuddy.dto.auth.AuthLoginRequest;
import nl.moviebuddy.dto.auth.AuthRegisterRequest;
import nl.moviebuddy.dto.auth.AuthResponse;
import nl.moviebuddy.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
}
