package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

    @RequestMapping("/clientDashboard")
    public String view1(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedinUser", user);
        return "clientDashboard";
    }

    @RequestMapping("/order")
    public String view2(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedinUser", user);
        return "order";
    }

    @RequestMapping("/points")
    public String view3(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedinUser", user);
        return "points";
    }
}
