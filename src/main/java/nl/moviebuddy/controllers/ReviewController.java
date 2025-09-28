package nl.moviebuddy.controllers;

import jakarta.validation.Valid;
import nl.moviebuddy.dto.review.ReviewCreateRequest;
import nl.moviebuddy.dto.review.ReviewResponse;
import nl.moviebuddy.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponse> create(@Valid @RequestBody ReviewCreateRequest req) {
        ReviewResponse created = reviewService.createReview(req);
        return ResponseEntity.created(URI.create("/reviews/" + created.getId())).body(created);
    }

    @GetMapping("/movies/{movieId}/reviews")
    public List<ReviewResponse> listByMovie(@PathVariable Long movieId) {
        return reviewService.listByMovie(movieId);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId, @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }
}
