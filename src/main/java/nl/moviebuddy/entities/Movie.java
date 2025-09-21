package nl.moviebuddy.entities;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity @Table(name = "movies")
@Getter @Setter
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(length=1500)
    private String description;

    private Integer releaseYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
