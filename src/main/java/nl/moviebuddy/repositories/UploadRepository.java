package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {
    List<Upload> findByMovieId(Long movieId);
}

