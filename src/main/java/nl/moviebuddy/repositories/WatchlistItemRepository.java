package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistItemRepository extends JpaRepository<WatchlistItem, Long> {
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
    List<WatchlistItem> findByUserId(Long userId);
}
