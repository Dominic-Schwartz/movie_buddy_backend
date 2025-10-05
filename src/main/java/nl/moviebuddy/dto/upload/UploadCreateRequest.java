package nl.moviebuddy.dto.upload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadCreateRequest {
    @NotNull
    private Long movieId;

    @NotNull @Size(min = 1, max = 255)
    private String filename;

    @NotNull @Size(min = 1, max = 100)
    private String contentType;
}
