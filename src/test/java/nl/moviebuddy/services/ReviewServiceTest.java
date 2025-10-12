package nl.moviebuddy.services;

import nl.moviebuddy.dto.review.ReviewCreateRequest;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.Review;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.ReviewRepository;
import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.services.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    private ReviewRepository reviewRepo;
    private UserRepository userRepo;
    private MovieRepository movieRepo;
    private ReviewService service;

    @BeforeEach
    void setup() {
        reviewRepo = mock(ReviewRepository.class);
        userRepo = mock(UserRepository.class);
        movieRepo = mock(MovieRepository.class);
        service = new ReviewServiceImpl(reviewRepo, userRepo, movieRepo);
    }

    @Test
    void createReview_succeeds_whenValid() {
        var req = new ReviewCreateRequest();
        req.setUserId(1L);
        req.setMovieId(10L);
        req.setRating(8);
        req.setReviewText("Leuke film!");

        var u = new User(); u.setId(1L); u.setUsername("buddytester");
        var m = new Movie(); m.setId(10L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(u));
        when(movieRepo.findById(10L)).thenReturn(Optional.of(m));
        when(reviewRepo.save(any())).thenAnswer(inv -> {
            Review r = inv.getArgument(0);
            r.setId(777L);
            r.setCreatedAt(Instant.now());
            r.setUser(u);
            r.setMovie(m);
            return r;
        });

        var resp = service.createReview(req);

        assertEquals(777L, resp.getId());
        assertEquals(1L, resp.getUserId());
        assertEquals(10L, resp.getMovieId());
        assertEquals(8, resp.getRating());
        assertEquals("Leuke film!", resp.getReviewText());
        verify(reviewRepo, times(1)).save(any(Review.class));
    }
}
