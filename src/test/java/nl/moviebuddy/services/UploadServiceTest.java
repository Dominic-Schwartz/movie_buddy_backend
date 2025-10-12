package nl.moviebuddy.services;

import nl.moviebuddy.dto.upload.UploadCreateRequest;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.Upload;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.UploadRepository;
import nl.moviebuddy.services.impl.UploadServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UploadServiceTest {

    private UploadRepository uploadRepo;
    private MovieRepository movieRepo;
    private UploadService service;

    @BeforeEach
    void setup() {
        uploadRepo = mock(UploadRepository.class);
        movieRepo = mock(MovieRepository.class);
        service = new UploadServiceImpl(uploadRepo, movieRepo);
    }

    @Test
    void createUpload_succeeds_whenValid() {
        var req = new UploadCreateRequest();
        req.setMovieId(10L);
        req.setFilename("poster.jpg");
        req.setContentType("image/jpeg");

        var m = new Movie(); m.setId(10L);

        when(movieRepo.findById(10L)).thenReturn(Optional.of(m));
        when(uploadRepo.save(any())).thenAnswer(inv -> {
            Upload u = inv.getArgument(0);
            u.setId(555L);
            u.setUploadDate(Instant.now());
            return u;
        });

        var resp = service.create(req);

        assertEquals(555L, resp.getId());
        verify(uploadRepo).save(any(Upload.class));
    }
}
