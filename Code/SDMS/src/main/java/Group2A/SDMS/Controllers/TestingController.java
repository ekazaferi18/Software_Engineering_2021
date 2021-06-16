package Group2A.SDMS.Controllers;

import Group2A.SDMS.Entities.Products;
import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Repositories.ProductRepository;
import Group2A.SDMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestingController {

    @Autowired// This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserService userService;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/GreetinTest")
    public String login(Model model) {
        return "greeting";
    }

    @RequestMapping("/logoutTest")
    public String logoutTest(Model model) {
        return "logoutTest";
    }

    @RequestMapping("/testProductGet")
    public List<Products> logoutTest() {
        return productRepository.getAllProducts();
    }

    @RequestMapping("/addAdminUser")
    public String admin(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        Users user = new Users();

        user.setUsername("Admin");
        String password = "Admin";
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user.setPassword(bcp.encode(password));
        user.setFirstname("Group");
        user.setLastname("2A");
        user.setEmail("Interesting@Example.Com");
        user.setAddress("Somewhere, Someplace.");
        user.setAccessLevel("ADMIN");
        user.setTotalPoints(0);
        user.setWage(0);

        userService.saveUser(user);

        return "greeting";
    }

    @RequestMapping("/addNormie")
    public String normie(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        Users user = new Users();

        user.setUsername("Normie");
        String password = "Badass";
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user.setPassword(bcp.encode(password));
        user.setFirstname("Jim");
        user.setLastname("Tuskes");
        user.setEmail("Interesting@Example2.Com");
        user.setAddress("Somewhere, Someplace. Maybe Lost?");
        user.setAccessLevel("USER");
        user.setTotalPoints(0);
        user.setWage(0);

        userService.saveUser(user);

        return "greeting";
    }

}
