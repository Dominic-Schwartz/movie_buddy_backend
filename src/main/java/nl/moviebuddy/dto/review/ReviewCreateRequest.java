package nl.moviebuddy.dto.review;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @Min(1) @Max(10)
    private Integer rating;

    @Size(max = 2000)
    private String reviewText;
}
