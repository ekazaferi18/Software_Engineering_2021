package Group2A.SDMS.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }
}
