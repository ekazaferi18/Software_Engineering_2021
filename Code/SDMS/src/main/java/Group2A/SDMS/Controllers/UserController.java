package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Services.CategoryService;
import Group2A.SDMS.Services.ProductService;
import Group2A.SDMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private List<Users> usersList;

    private PrincipalUser user;

    @RequestMapping("/userManagement")
    public String getAllUsers(Model model) {
        user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usersList = userService.getAllUsers();
        System.out.println("method reached");
        model.addAttribute("users", usersList);
        model.addAttribute("loggedinUser", user);
        return "UserManagement";
    }

    @RequestMapping("/editUser")
    public String editUserModule(@RequestParam String username, Model model){
        user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users selectedUser = userService.getUserByUsername(username);
        model.addAttribute("users", usersList);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("loggedinUser", user);
        return "UserManagement";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam int userID, Model model){
        user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteUserByID(userID);
        model.addAttribute("users", usersList);
        model.addAttribute("loggedinUser", user);
        return "redirect:/userManagement";
    }

    @RequestMapping("/addUser")
    public String addUserModule(Model model){
        user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        model.addAttribute("loggedinUser", user);
        return "UserManagement";
    }

    @RequestMapping("/createUser")
    public String createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password,
                             @RequestParam String firstname, @RequestParam String lastname, @RequestParam String address,
                             @RequestParam double wage, @RequestParam String accessLevel, Model model){
        user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        model.addAttribute("loggedinUser", user);

        Users user1 = new Users();

        user1.setUsername(username);
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user1.setPassword(bcp.encode(password));
        user1.setFirstname(firstname);
        user1.setLastname(lastname);
        user1.setEmail(email);
        user1.setAddress(address);
        user1.setAccessLevel(accessLevel);
        user1.setTotalPoints(0);
        user1.setWage(wage);

        if (userService.getUserByUsername(username) == null) {
            userService.saveUser(user1);
            return "redirect:/userManagement";
        }
        else{ return "redirect:/addUser?usernameError=true&createMode=true";}
    }

    @PostMapping("/saveEdittedUser")
    public String saveEdittedUser(@RequestParam String username, @RequestParam String email,
                                  @RequestParam String firstname, @RequestParam String lastname,
                                  @RequestParam String address, @RequestParam double wage){
        userService.editUser(username, email, firstname, lastname, address, wage);
        return "redirect:/userManagement";
    }

    @PostMapping("/saveEdittedUserPassword")
    public String saveEdittedUser(@RequestParam String username, @RequestParam String password){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        PrincipalUser authenticatedUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.getUserByUsername(username);
        user.setPassword(bc.encode(password));
        userService.save(user);
        return "redirect:/userManagement";
    }
}
