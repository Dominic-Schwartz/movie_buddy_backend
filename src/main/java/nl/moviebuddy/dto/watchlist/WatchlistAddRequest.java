package nl.moviebuddy.dto.watchlist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistAddRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @NotNull
    private String status;

    @Size(max = 500)
    private String note;
}
