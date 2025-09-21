package nl.moviebuddy.services;

import nl.moviebuddy.dto.review.ReviewCreateRequest;
import nl.moviebuddy.dto.review.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse createReview(ReviewCreateRequest req);
    List<ReviewResponse> listByMovie(Long movieId);
    void deleteReview(Long reviewId, Long userId);
}
