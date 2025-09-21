package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.genre.GenreResponse;
import nl.moviebuddy.repositories.GenreRepository;
import nl.moviebuddy.services.GenreService;
import nl.moviebuddy.util.GenreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;

    public GenreServiceImpl(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<GenreResponse> listAll() {
        return genreRepo.findAll().stream()
                .map(GenreMapper::toResponse)
                .toList();
    }
}
