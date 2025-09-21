package nl.moviebuddy.services;

import nl.moviebuddy.dto.genre.GenreResponse;
import java.util.List;

public interface GenreService {
    List<GenreResponse> listAll();
}

