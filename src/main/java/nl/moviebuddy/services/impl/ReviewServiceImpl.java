package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.review.ReviewCreateRequest;
import nl.moviebuddy.dto.review.ReviewResponse;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.Review;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.exceptions.NotFoundException;
import nl.moviebuddy.exceptions.ConflictException;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.ReviewRepository;
import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.services.ReviewService;
import nl.moviebuddy.util.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final MovieRepository movieRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo,
                             UserRepository userRepo,
                             MovieRepository movieRepo) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public ReviewResponse createReview(ReviewCreateRequest req) {
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found: " + req.getUserId()));

        Movie movie = movieRepo.findById(req.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found: " + req.getMovieId()));

        if (reviewRepo.existsByUserIdAndMovieId(user.getId(), movie.getId())) {
            throw new ConflictException("User already reviewed this movie");
        }

        Review r = new Review();
        r.setUser(user);
        r.setMovie(movie);
        r.setRating(req.getRating());
        r.setReviewText(req.getReviewText());

        Review saved = reviewRepo.save(r);
        return ReviewMapper.toResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> listByMovie(Long movieId) {
        return reviewRepo.findByMovieId(movieId).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {
        var review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review not found: " + reviewId));
        if (!review.getUser().getId().equals(userId)) {
            throw new NotFoundException("Review not found for this user");
        }
        reviewRepo.delete(review);
    }
}
