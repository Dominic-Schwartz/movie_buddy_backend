package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
