package pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByNameIgnoreCase(String name);
}
