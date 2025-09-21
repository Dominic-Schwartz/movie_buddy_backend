package nl.moviebuddy.services;

import nl.moviebuddy.dto.movie.MovieResponse;
import org.springframework.data.domain.Page;

public interface MovieService {
    Page<MovieResponse> search(String title, Long genreId, Integer year, int page, int size);
}
