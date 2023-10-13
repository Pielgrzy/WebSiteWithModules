package pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.genre;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.genre.dto.GenreDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<GenreDto> findGenreByName(String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .map(GenreDtoMapper::mapperDto);
    }

    public List<GenreDto> findAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreDtoMapper::mapperDto)
                .toList();
    }

    @Transactional
    public void addGenre(GenreDto genreDto) {
        Genre genreToSave = new Genre();
        genreToSave.setName(genreDto.getName());
        genreToSave.setDescription(genreDto.getDescription());
        genreRepository.save(genreToSave);
    }
}
