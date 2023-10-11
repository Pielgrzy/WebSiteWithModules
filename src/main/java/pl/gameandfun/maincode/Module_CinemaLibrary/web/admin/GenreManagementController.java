package pl.gameandfun.maincode.Module_CinemaLibrary.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.GenreService;
import pl.gameandfun.maincode.Module_CinemaLibrary.genre.dto.GenreDto;

@Controller
@RequestMapping("/CinemaLibrary")
public class GenreManagementController {

    final private GenreService genreService;

    public GenreManagementController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-gatunek")
    public String addGenreForm(Model model) {
        GenreDto genreDto = new GenreDto();
        model.addAttribute("genre", genreDto);
        return "Module_CinemaLibrary/admin/genre-form";
    }

    @PostMapping("/admin/dodaj-gatunek")
    public String addGenre(GenreDto genreDto, RedirectAttributes redirectAttributes) {
        genreService.addGenre(genreDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zapisany".formatted(genreDto.getName()));
        return "redirect:/CinemaLibrary/admin";
    }

}
