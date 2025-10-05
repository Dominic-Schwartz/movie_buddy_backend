package nl.moviebuddy.dto.genre;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponse {
    private Long id;
    private String name;
    private String description;
}
