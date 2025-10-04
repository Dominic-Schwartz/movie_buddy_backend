package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.auth.AuthLoginRequest;
import nl.moviebuddy.dto.auth.AuthRegisterRequest;
import nl.moviebuddy.dto.auth.AuthResponse;
import nl.moviebuddy.entities.Role;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.exceptions.ConflictException;
import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.services.AuthService;
import nl.moviebuddy.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthServiceImpl(UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwt) {
        this.userRepo = userRepo; this.encoder = encoder; this.jwt = jwt;
    }

    @Override
    public AuthResponse register(AuthRegisterRequest req) {
        if (userRepo.existsByUsernameOrEmail(req.getUsername(), req.getEmail())) {
            throw new ConflictException("Username or email already in use");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPasswordHash(encoder.encode(req.getPassword()));
        u.setRole(Role.ROLE_USER);

        User saved = userRepo.save(u);
        String token = jwt.generateToken(saved.getUsername(), saved.getRole().name());
        return new AuthResponse(token, saved.getUsername(), saved.getRole().name());
    }

    @Override
    public AuthResponse login(AuthLoginRequest req) {
        User u = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new nl.moviebuddy.exceptions.NotFoundException("User not found"));

        if (!encoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new ConflictException("Invalid credentials");
        }
        String token = jwt.generateToken(u.getUsername(), u.getRole().name());
        return new AuthResponse(token, u.getUsername(), u.getRole().name());
    }
}
