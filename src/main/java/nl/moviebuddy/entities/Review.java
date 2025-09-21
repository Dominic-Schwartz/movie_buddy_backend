package nl.moviebuddy.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","movie_id"})
)
@Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(10)
    @Column(nullable = false)
    private Integer rating;

    @Column(length = 2000)
    private String reviewText;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
