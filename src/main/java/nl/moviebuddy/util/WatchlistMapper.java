package nl.moviebuddy.util;

import nl.moviebuddy.dto.watchlist.WatchlistItemResponse;
import nl.moviebuddy.entities.WatchlistItem;

public class WatchlistMapper {
    public static WatchlistItemResponse toResponse(WatchlistItem wi) {
        WatchlistItemResponse dto = new WatchlistItemResponse();
        dto.setId(wi.getId());
        dto.setUserId(wi.getUser().getId());
        dto.setMovieId(wi.getMovie().getId());
        dto.setStatus(wi.getStatus());
        dto.setNote(wi.getNote());
        dto.setDateAdded(wi.getDateAdded());
        dto.setMovieTitle(wi.getMovie().getTitle());
        return dto;
    }
}
