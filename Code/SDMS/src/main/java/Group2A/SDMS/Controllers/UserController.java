package Group2A.SDMS.Controllers;

import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Repositories.UserRepository;
import Group2A.SDMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin")
    public @ResponseBody Iterable<Users> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.getAllUsers();
    }
}
