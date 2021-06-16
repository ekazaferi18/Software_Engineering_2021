package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.ProductCategories;
import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Services.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private List<ProductCategories> categories;

    @RequestMapping("/categoryManagement")
    public String getAllUsers(Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categories = categoryService.getAllCategories();
        System.out.println("method reached");
        model.addAttribute("categories", categories);
        model.addAttribute("loggedinUser", user);
        return "CategoryManagement";
    }

    @RequestMapping("/editCategory")
    public String editUserModule(@RequestParam int categoryID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProductCategories selectedCategory = categoryService.getCategoryID(categoryID);
        categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("loggedinUser", user);
        return "CategoryManagement";
    }

    @RequestMapping("/deleteCategory")
    public String deleteUser(@RequestParam int categoryID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categoryService.deleteCategoryByID(categoryID);
        categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("loggedinUser", user);
        return "redirect:/categoryManagement";
    }

    @RequestMapping("/addCategory")
    public String addUserModule(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("loggedinUser", user);
        return "CategoryManagement";
    }

    @RequestMapping("/createCategory")
    public String createUser(@RequestParam String name, @RequestParam String description, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("loggedinUser", user);

        ProductCategories category = new ProductCategories();

        category.setDescription(description);
        category.setName(name);

        if (categoryService.getCategoryName(name) == null) {
            categoryService.saveCategory(category);
            return "redirect:/categoryManagement";
        }
        else{ return "redirect:/addCategory?usernameError=true&createMode=true";}
    }

    @PostMapping("/saveEdittedCategory")
    public String saveEdittedUser(@RequestParam int categoryID, @RequestParam String name,
                                  @RequestParam String description){

        ProductCategories category = new ProductCategories();

        category.setName(name);
        category.setCategoryID(categoryID);
        category.setDescription(description);

        categoryService.saveCategory(category);

        return "redirect:/categoryManagement";
    }


}
