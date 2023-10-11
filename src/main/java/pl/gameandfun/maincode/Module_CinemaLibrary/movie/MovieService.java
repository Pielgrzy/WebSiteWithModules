package pl.gameandfun.maincode.Module_CinemaLibrary.movie;

import org.springframework.stereotype.Service;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.Genre;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.GenreRepository;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.dto.MovieDto;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.dto.MovieSaveDto;
import pl.gameandfun.maincode.Module_CinemaLibrary.storage.FileStorageService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, FileStorageService fileStorageService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue().stream()
                .map(MovieDtoMapper::mapperDto)
                .toList();
    }

    public Optional<MovieDto> findMovieById(long id) {
        return movieRepository.findById(id).map(MovieDtoMapper::mapperDto);
    }

    public List<MovieDto> findMoviesByGenreName(String genreName) {
        return movieRepository.findAllByGenre_NameIgnoreCase(genreName)
                .stream()
                .map(MovieDtoMapper::mapperDto)
                .toList();
    }

    public void addMovie(MovieSaveDto movieToSave) {
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOriginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToSave.getPoster() != null) {
            String savedFileName = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(savedFileName);
        }
        movieRepository.save(movie);
    }

}
