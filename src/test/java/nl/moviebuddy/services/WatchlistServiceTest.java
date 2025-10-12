package nl.moviebuddy.services;

import nl.moviebuddy.dto.watchlist.WatchlistAddRequest;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.entities.WatchlistItem;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.repositories.WatchlistItemRepository;
import nl.moviebuddy.services.impl.WatchlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchlistServiceTest {

    private WatchlistItemRepository watchlistRepo;
    private UserRepository userRepo;
    private MovieRepository movieRepo;
    private WatchlistService service;

    @BeforeEach
    void setup() {
        watchlistRepo = mock(WatchlistItemRepository.class);
        userRepo = mock(UserRepository.class);
        movieRepo = mock(MovieRepository.class);
        service = new WatchlistServiceImpl(watchlistRepo, userRepo, movieRepo);
    }

    @Test
    void addItem_createsNew_whenNotDuplicate() {
        var req = new WatchlistAddRequest(1L, 10L, "TO_WATCH", "Eerst trailer kijken");

        var u = new User(); u.setId(1L);
        var m = new Movie(); m.setId(10L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(u));
        when(movieRepo.findById(10L)).thenReturn(Optional.of(m));
        when(watchlistRepo.existsByUserIdAndMovieId(1L, 10L)).thenReturn(false);
        when(watchlistRepo.save(any())).thenAnswer(inv -> {
            WatchlistItem it = inv.getArgument(0);
            it.setId(999L);
            return it;
        });

        var resp = service.addItem(req);

        ArgumentCaptor<WatchlistItem> captor = ArgumentCaptor.forClass(WatchlistItem.class);
        verify(watchlistRepo).save(captor.capture());
        var saved = captor.getValue();

        assertEquals(1L, saved.getUser().getId());
        assertEquals(10L, saved.getMovie().getId());
        assertEquals("TO_WATCH", saved.getStatus());
        assertEquals("Eerst trailer kijken", saved.getNote());
        assertEquals(999L, resp.getId());
    }
}
