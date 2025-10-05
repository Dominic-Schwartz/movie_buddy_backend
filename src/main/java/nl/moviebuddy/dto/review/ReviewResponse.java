package nl.moviebuddy.dto.review;

import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long userId;
    private Long movieId;
    private String username;
    private Integer rating;
    private String reviewText;
    private Instant createdAt;
}
