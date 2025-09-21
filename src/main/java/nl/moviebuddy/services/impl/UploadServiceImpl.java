package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.upload.UploadCreateRequest;
import nl.moviebuddy.dto.upload.UploadResponse;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.Upload;
import nl.moviebuddy.exceptions.NotFoundException;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.UploadRepository;
import nl.moviebuddy.services.UploadService;
import nl.moviebuddy.util.UploadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepo;
    private final MovieRepository movieRepo;

    public UploadServiceImpl(UploadRepository uploadRepo, MovieRepository movieRepo) {
        this.uploadRepo = uploadRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public UploadResponse create(UploadCreateRequest req) {
        Movie movie = movieRepo.findById(req.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found: " + req.getMovieId()));

        Upload u = new Upload();
        u.setFilename(req.getFilename());
        u.setContentType(req.getContentType());
        u.setMovie(movie);

        Upload saved = uploadRepo.save(u);
        return UploadMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UploadResponse> listByMovie(Long movieId) {
        return uploadRepo.findByMovieId(movieId).stream()
                .map(UploadMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long uploadId) {
        uploadRepo.deleteById(uploadId);
    }
}
