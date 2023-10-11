package pl.gameandfun.maincode.Module_CinemaLibrary.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.GenreService;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.dto.GenreDto;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.MovieService;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.dto.MovieDto;

import java.util.List;

@Controller
@RequestMapping("/CinemaLibrary")
public class GenreController {
    private final GenreService genreService;
    private final MovieService movieService;

    public GenreController(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @GetMapping("/gatunek/{genreName}")
    public String getGenre(@PathVariable String genreName, Model model) {
        GenreDto genreDto = genreService.findGenreByName(genreName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<MovieDto> movieDto = movieService.findMoviesByGenreName(genreName);
        model.addAttribute("heading", genreDto.getName());
        model.addAttribute("description", genreDto.getDescription());
        model.addAttribute("movies", movieDto);
        return "Module_CinemaLibrary/movie-listing";
    }

    @GetMapping("/gatunki-lista")
    public String getGenreList(Model model) {
        List<GenreDto> genreDto = genreService.findAllGenres();
        model.addAttribute("genre", genreDto);
        return "Module_CinemaLibrary/genre-list";
    }
}
