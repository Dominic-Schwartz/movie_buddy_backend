package nl.moviebuddy.util;

import nl.moviebuddy.dto.user.UserResponse;
import nl.moviebuddy.entities.User;

public class UserMapper {
    public static UserResponse toResponse(User u) {
        UserResponse dto = new UserResponse();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole().name());
        return dto;
    }
}
