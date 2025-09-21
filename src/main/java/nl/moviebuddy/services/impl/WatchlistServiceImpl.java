package nl.moviebuddy.services.impl;

import nl.moviebuddy.dto.watchlist.WatchlistAddRequest;
import nl.moviebuddy.dto.watchlist.WatchlistItemResponse;
import nl.moviebuddy.entities.Movie;
import nl.moviebuddy.entities.User;
import nl.moviebuddy.entities.WatchlistItem;
import nl.moviebuddy.repositories.MovieRepository;
import nl.moviebuddy.repositories.UserRepository;
import nl.moviebuddy.repositories.WatchlistItemRepository;
import nl.moviebuddy.services.WatchlistService;
import nl.moviebuddy.util.WatchlistMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nl.moviebuddy.exceptions.NotFoundException;
import nl.moviebuddy.exceptions.ConflictException;
import java.util.List;

@Service
@Transactional
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistItemRepository watchlistRepo;
    private final UserRepository userRepo;
    private final MovieRepository movieRepo;

    public WatchlistServiceImpl(WatchlistItemRepository watchlistRepo,
                                UserRepository userRepo,
                                MovieRepository movieRepo) {
        this.watchlistRepo = watchlistRepo;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public WatchlistItemResponse addItem(WatchlistAddRequest req) {
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found: " + req.getUserId()));

        Movie movie = movieRepo.findById(req.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found: " + req.getMovieId()));

        if (watchlistRepo.existsByUserIdAndMovieId(user.getId(), movie.getId())) {
            throw new ConflictException("Movie already in watchlist for this user");
        }

        WatchlistItem wi = new WatchlistItem();
        wi.setUser(user);
        wi.setMovie(movie);
        wi.setStatus(req.getStatus());
        wi.setNote(req.getNote());

        WatchlistItem saved = watchlistRepo.save(wi);

        return WatchlistMapper.toResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<WatchlistItemResponse> listByUser(Long userId) {
        return watchlistRepo.findByUserId(userId).stream()
                .map(WatchlistMapper::toResponse)
                .toList();
    }

    @Override
    public void removeItem(Long itemId, Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
