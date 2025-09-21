package nl.moviebuddy.util;

import nl.moviebuddy.dto.genre.GenreResponse;
import nl.moviebuddy.entities.Genre;

public class GenreMapper {
    public static GenreResponse toResponse(Genre g) {
        GenreResponse dto = new GenreResponse();
        dto.setId(g.getId());
        dto.setName(g.getName());
        dto.setDescription(g.getDescription());
        return dto;
    }
}
