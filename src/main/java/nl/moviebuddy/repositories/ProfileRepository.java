package nl.moviebuddy.repositories;

import nl.moviebuddy.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
