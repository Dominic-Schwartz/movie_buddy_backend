package nl.moviebuddy.entities;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity @Table(name = "genres")
@Getter @Setter
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 300)
    private String description;
}
