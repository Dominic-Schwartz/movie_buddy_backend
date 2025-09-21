package nl.moviebuddy.services;

import nl.moviebuddy.dto.watchlist.WatchlistAddRequest;
import nl.moviebuddy.dto.watchlist.WatchlistItemResponse;

import java.util.List;

public interface WatchlistService {
    WatchlistItemResponse addItem(WatchlistAddRequest req);
    List<WatchlistItemResponse> listByUser(Long userId);
    void removeItem(Long itemId, Long userId);
}
