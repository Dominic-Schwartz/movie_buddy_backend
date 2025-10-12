package nl.moviebuddy.services;

import nl.moviebuddy.dto.review.ReviewCreateRequest;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.Review;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.exceptions.ConflictException;
import nl.moviebuddy.exceptions.NotFoundException;
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
        when(reviewRepo.existsByUserIdAndMovieId(1L, 10L)).thenReturn(false);
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

    @Test
    void createReview_userNotFound_throws() {
        var req = new ReviewCreateRequest();
        req.setUserId(42L);
        req.setMovieId(10L);
        req.setRating(7);
        req.setReviewText("x");

        when(userRepo.findById(42L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.createReview(req));

        verify(movieRepo, never()).findById(anyLong());
        verify(reviewRepo, never()).existsByUserIdAndMovieId(anyLong(), anyLong());
        verify(reviewRepo, never()).save(any());
    }

    @Test
    void createReview_movieNotFound_throws() {
        var req = new ReviewCreateRequest();
        req.setUserId(1L);
        req.setMovieId(404L);
        req.setRating(7);
        req.setReviewText("x");

        var u = new User(); u.setId(1L);
        when(userRepo.findById(1L)).thenReturn(Optional.of(u));
        when(movieRepo.findById(404L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.createReview(req));

        verify(reviewRepo, never()).existsByUserIdAndMovieId(anyLong(), anyLong());
        verify(reviewRepo, never()).save(any());
    }

    @Test
    void createReview_duplicate_throwsConflict() {
        var req = new ReviewCreateRequest();
        req.setUserId(1L);
        req.setMovieId(10L);
        req.setRating(9);
        req.setReviewText("dup");

        var u = new User(); u.setId(1L);
        var m = new Movie(); m.setId(10L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(u));
        when(movieRepo.findById(10L)).thenReturn(Optional.of(m));
        when(reviewRepo.existsByUserIdAndMovieId(1L, 10L)).thenReturn(true);

        assertThrows(ConflictException.class, () -> service.createReview(req));
        verify(reviewRepo, never()).save(any());
    }

    @Test
    void deleteReview_happyPath_callsDelete() {
        var u = new User(); u.setId(1L);
        var m = new Movie(); m.setId(10L);
        var r = new Review(); r.setId(999L); r.setUser(u); r.setMovie(m);

        when(reviewRepo.findById(999L)).thenReturn(Optional.of(r));

        service.deleteReview(999L, 1L);

        verify(reviewRepo, times(1)).delete(r);
    }

    @Test
    void deleteReview_wrongUser_throwsNotFound() {
        var owner = new User(); owner.setId(1L);
        var r = new Review(); r.setId(1000L); r.setUser(owner);

        when(reviewRepo.findById(1000L)).thenReturn(Optional.of(r));

        assertThrows(NotFoundException.class, () -> service.deleteReview(1000L, 2L));
        verify(reviewRepo, never()).delete(any());
    }
}
