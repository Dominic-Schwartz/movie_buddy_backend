package nl.moviebuddy.util;

import nl.moviebuddy.dto.review.ReviewResponse;
import nl.moviebuddy.entities.Review;

public class ReviewMapper {
    public static ReviewResponse toResponse(Review r) {
        ReviewResponse dto = new ReviewResponse();
        dto.setId(r.getId());
        dto.setUserId(r.getUser().getId());
        dto.setMovieId(r.getMovie().getId());
        dto.setUsername(r.getUser().getUsername());
        dto.setRating(r.getRating());
        dto.setReviewText(r.getReviewText());
        dto.setCreatedAt(r.getCreatedAt());
        return dto;
    }
}
