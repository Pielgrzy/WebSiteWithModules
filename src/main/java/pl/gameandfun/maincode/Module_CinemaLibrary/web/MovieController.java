package pl.gameandfun.maincode.Module_CinemaLibrary.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.movie.MovieService;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.movie.dto.MovieDto;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/CinemaLibrary/film/{id}")
    public String getMovie(@PathVariable long id, Model model) {
        MovieDto movieDto = movieService.findMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movieDto);
        return "Module_CinemaLibrary/movie";
    }
}
