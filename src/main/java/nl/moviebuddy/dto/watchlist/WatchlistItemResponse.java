package nl.moviebuddy.dto.watchlist;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistItemResponse {
    private Long id;
    private Long userId;
    private Long movieId;
    private String status;
    private String note;
    private LocalDate dateAdded;
    private String movieTitle;
}
