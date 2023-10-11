package pl.gameandfun.maincode.Module_CinemaLibrary.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.GenreService;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.dto.GenreDto;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.MovieService;
import pl.gameandfun.maincode.Module_CinemaLibrary.movie.dto.MovieSaveDto;

import java.util.List;

@Controller
@RequestMapping("/Module_CinemaLibrary")
public class MovieManagementController {

    private final MovieService movieService;
    private final GenreService genreService;

    public MovieManagementController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-film")
    public String addMovieForm(Model model) {
        List<GenreDto> genreDtoAll = genreService.findAllGenres();
        model.addAttribute("genres", genreDtoAll);
        MovieSaveDto moveToSave = new MovieSaveDto();
        model.addAttribute("movie", moveToSave);
        return "Module_CinemaLibrary/admin/movie-form";
    }

    @PostMapping("/admin/dodaj-film")
    public String addMovie(MovieSaveDto movieSaveDto, RedirectAttributes redirectAttributes) {
        movieService.addMovie(movieSaveDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(movieSaveDto.getTitle()));
        return "redirect:/Module_CinemaLibrary/admin";
    }
}
