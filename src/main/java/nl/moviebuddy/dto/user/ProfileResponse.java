package nl.moviebuddy.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileResponse {
    private Long id;
    private String avatarUrl;
    private String bio;
    private Long userId;
}
