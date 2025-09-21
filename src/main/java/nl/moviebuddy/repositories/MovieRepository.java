package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
         select m from Movie m
         where (:title is null or lower(m.title) like lower(concat('%', :title, '%')))
           and (:genreId is null or m.genre.id = :genreId)
           and (:year is null or m.releaseYear = :year)
         """)
    Page<Movie> search(
            @Param("title") String title,
            @Param("genreId") Long genreId,
            @Param("year") Integer year,
            Pageable pageable
    );
}
