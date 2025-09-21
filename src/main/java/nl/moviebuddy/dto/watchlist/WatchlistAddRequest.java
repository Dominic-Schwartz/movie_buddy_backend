package nl.moviebuddy.dto.watchlist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WatchlistAddRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @NotNull
    private String status;

    @Size(max = 500)
    private String note;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
