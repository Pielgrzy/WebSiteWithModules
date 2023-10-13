package pl.gameandfun.maincode.Module_CinemaLibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.movie.MovieService;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.movie.dto.MovieDto;

import java.util.List;

@Controller
public class HomeController {
    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/CinemaLibrary")
    public String home(Model model) {
        List<MovieDto> promotedMovies = movieService.findAllPromotedMovies();
        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Polecane filmy");
        model.addAttribute("movies", promotedMovies);
        return "Module_CinemaLibrary/movie-listing";
    }

    @GetMapping("/CinemaLibrary/movie")
    public String movie() {
        return "Module_CinemaLibrary/movie";
    }

}
