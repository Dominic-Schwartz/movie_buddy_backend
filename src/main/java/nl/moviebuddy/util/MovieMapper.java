package nl.moviebuddy.util;

import nl.moviebuddy.dto.movie.MovieResponse;
import nl.moviebuddy.entities.Movie;

public class MovieMapper {
    public static MovieResponse toResponse(Movie m) {
        MovieResponse dto = new MovieResponse();
        dto.setId(m.getId());
        dto.setTitle(m.getTitle());
        dto.setDescription(m.getDescription());
        dto.setReleaseYear(m.getReleaseYear());
        dto.setGenreId(m.getGenre().getId());
        dto.setGenreName(m.getGenre().getName());
        return dto;
    }
}
