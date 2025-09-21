package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.movie.MovieResponse;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.services.MovieService;
import nl.moviebuddy.util.MovieMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Page<MovieResponse> search(String title, Long genreId, Integer year, int page, int size) {
        var pageable = PageRequest.of(Math.max(page,0), Math.max(size,1));
        return movieRepo.search(
                        (title == null || title.isBlank()) ? null : title.trim(),
                        genreId,
                        year,
                        pageable
                )
                .map(MovieMapper::toResponse);
    }
}
