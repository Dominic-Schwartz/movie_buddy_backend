package nl.moviebuddy.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private Long id;
    private String avatarUrl;
    private String bio;
    private Long userId;
}
