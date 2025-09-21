package nl.moviebuddy.entities;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "uploads")
@Getter @Setter
public class Upload {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Instant uploadDate = Instant.now();

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
