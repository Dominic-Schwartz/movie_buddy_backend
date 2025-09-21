package nl.moviebuddy.entities;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "watchlist_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","movie_id"})
)
@Getter @Setter
public class WatchlistItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateAdded = LocalDate.now();

    @Column(nullable = false)
    private String status;

    @Column(length = 500)
    private String note;

    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
