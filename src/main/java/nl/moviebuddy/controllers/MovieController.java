package nl.moviebuddy.controllers;

import nl.moviebuddy.dto.movie.MovieResponse;
import nl.moviebuddy.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) { this.movieService = movieService; }

    @GetMapping("/search")
    public Page<MovieResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return movieService.search(title, genreId, year, page, size);
    }
}
