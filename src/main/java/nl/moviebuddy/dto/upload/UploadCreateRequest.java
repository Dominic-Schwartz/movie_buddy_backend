package nl.moviebuddy.dto.upload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UploadCreateRequest {
    @NotNull
    private Long movieId;

    @NotNull
    @Size(min = 1, max = 255)
    private String filename;

    @NotNull
    @Size(min = 1, max = 100)
    private String contentType;

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}
