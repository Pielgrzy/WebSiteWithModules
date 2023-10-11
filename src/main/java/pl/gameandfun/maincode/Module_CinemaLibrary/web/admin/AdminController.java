package pl.gameandfun.maincode.Module_CinemaLibrary.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    @GetMapping("/CinemaLibrary/admin")
    public String getAdminPanel() {
        return "Module_CinemaLibrary/admin/admin";
    }
}
