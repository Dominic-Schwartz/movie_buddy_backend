package nl.moviebuddy.controllers;

import jakarta.validation.Valid;
import nl.moviebuddy.dto.watchlist.WatchlistAddRequest;
import nl.moviebuddy.dto.watchlist.WatchlistItemResponse;
import nl.moviebuddy.services.WatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;
    public WatchlistController(WatchlistService watchlistService) { this.watchlistService = watchlistService; }

    @PostMapping
    public ResponseEntity<WatchlistItemResponse> add(
            @PathVariable Long userId,
            @Valid @RequestBody WatchlistAddRequest req
    ) {
        req.setUserId(userId);
        WatchlistItemResponse created = watchlistService.addItem(req);
        return ResponseEntity.created(URI.create("/users/" + userId + "/watchlist/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public List<WatchlistItemResponse> list(@PathVariable Long userId) {
        return watchlistService.listByUser(userId);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long itemId) {
        watchlistService.removeItem(itemId, userId);
        return ResponseEntity.noContent().build();
    }
}
