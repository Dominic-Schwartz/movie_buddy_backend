package nl.moviebuddy.services;

import nl.moviebuddy.dto.auth.AuthLoginRequest;
import nl.moviebuddy.dto.auth.AuthRegisterRequest;
import nl.moviebuddy.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest req);
    AuthResponse login(AuthLoginRequest req);
}
