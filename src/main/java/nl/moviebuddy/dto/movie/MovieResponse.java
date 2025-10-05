package nl.moviebuddy.dto.movie;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Long genreId;
    private String genreName;
}
