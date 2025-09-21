package nl.moviebuddy.services;

import nl.moviebuddy.dto.upload.UploadCreateRequest;
import nl.moviebuddy.dto.upload.UploadResponse;

import java.util.List;

public interface UploadService {
    UploadResponse create(UploadCreateRequest req);
    List<UploadResponse> listByMovie(Long movieId);
    void delete(Long uploadId);
}
