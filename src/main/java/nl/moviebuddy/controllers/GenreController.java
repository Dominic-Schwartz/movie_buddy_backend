package nl.moviebuddy.controllers;

import nl.moviebuddy.dto.genre.GenreResponse;
import nl.moviebuddy.services.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    public GenreController(GenreService genreService) { this.genreService = genreService; }

    @GetMapping
    public List<GenreResponse> list() {
        return genreService.listAll();
    }
}
