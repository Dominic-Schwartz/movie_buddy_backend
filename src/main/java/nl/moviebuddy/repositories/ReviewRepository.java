package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
}
