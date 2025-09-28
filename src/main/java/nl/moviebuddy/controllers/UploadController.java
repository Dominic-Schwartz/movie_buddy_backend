package nl.moviebuddy.controllers;

import jakarta.validation.Valid;
import nl.moviebuddy.dto.upload.UploadCreateRequest;
import nl.moviebuddy.dto.upload.UploadResponse;
import nl.moviebuddy.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class UploadController {

    private final UploadService uploadService;
    public UploadController(UploadService uploadService) { this.uploadService = uploadService; }

    @PostMapping("/uploads")
    public ResponseEntity<UploadResponse> create(@Valid @RequestBody UploadCreateRequest req) {
        UploadResponse created = uploadService.create(req);
        return ResponseEntity.created(URI.create("/uploads/" + created.getId())).body(created);
    }

    @GetMapping("/movies/{movieId}/uploads")
    public List<UploadResponse> listByMovie(@PathVariable Long movieId) {
        return uploadService.listByMovie(movieId);
    }

    @DeleteMapping("/uploads/{uploadId}")
    public ResponseEntity<Void> delete(@PathVariable Long uploadId) {
        uploadService.delete(uploadId);
        return ResponseEntity.noContent().build();
    }
}
