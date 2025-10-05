package nl.moviebuddy.dto.upload;

import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {
    private Long id;
    private String filename;
    private String contentType;
    private Instant uploadDate;
    private Long movieId;
}
