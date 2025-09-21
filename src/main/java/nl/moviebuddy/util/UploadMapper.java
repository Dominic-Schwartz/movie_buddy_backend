package nl.moviebuddy.util;

import nl.moviebuddy.dto.upload.UploadResponse;
import nl.moviebuddy.entities.Upload;

public class UploadMapper {
    public static UploadResponse toResponse(Upload u) {
        UploadResponse dto = new UploadResponse();
        dto.setId(u.getId());
        dto.setFilename(u.getFilename());
        dto.setContentType(u.getContentType());
        dto.setUploadDate(u.getUploadDate());
        dto.setMovieId(u.getMovie().getId());
        return dto;
    }
}
