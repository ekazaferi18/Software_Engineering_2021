package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password,
                               @RequestParam String firstname, @RequestParam String lastname, @RequestParam String address){

        Users user1 = new Users();

        user1.setUsername(username);
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user1.setPassword(bcp.encode(password));
        user1.setFirstname(firstname);
        user1.setLastname(lastname);
        user1.setEmail(email);
        user1.setAddress(address);
        user1.setAccessLevel("USER");
        user1.setTotalPoints(0);
        user1.setWage(0);

        if (userService.getUserByUsername(username) == null) {
            userService.saveUser(user1);
            return "redirect:/login?username="+username+"&password="+password;
        }
        else{ return "redirect:/register?usernameError=true";}
    }

    @RequestMapping("/userProfile")
    public String userProfile(Model model){
        PrincipalUser authenticatedUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedinUser", authenticatedUser);
        return "userProfile";
    }

    @PostMapping("/saveEdittedProfileDetails")
    public String saveEdittedUser(@RequestParam String username, @RequestParam String email,
                                  @RequestParam String firstname, @RequestParam String lastname,
                                  @RequestParam String address){
        PrincipalUser authenticatedUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1 = authenticatedUser.getUser();
        user1.setUsername(authenticatedUser.getUser().getUsername());
        user1.setEmail(email);
        user1.setFirstname(firstname);
        user1.setLastname(lastname);
        user1.setWage(authenticatedUser.getUser().getWage());
        authenticatedUser.setUser(user1);
        user1.setAddress(address);
        userService.editUser(authenticatedUser.getUser().getUsername(), email, firstname, lastname, address, authenticatedUser.getUser().getWage());
        return "redirect:/userProfile";
    }

    @PostMapping("/saveEdittedProfilePassword")
    public String saveEdittedUser(@RequestParam String password){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        PrincipalUser authenticatedUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = authenticatedUser.getUser();
        user.setPassword(bc.encode(password));
        userService.save(user);
        authenticatedUser.setUser(user);
        return "redirect:/userProfile";
    }
}
