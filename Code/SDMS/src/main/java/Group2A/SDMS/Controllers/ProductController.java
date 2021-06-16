package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.ProductCategories;
import Group2A.SDMS.Entities.Products;
import Group2A.SDMS.Services.CategoryService;
import Group2A.SDMS.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoriesService;

    @RequestMapping("/productManagement")
    public String getAllProducts(Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Products> productsList = productService.getAllProducts();
        List<ProductCategories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productsList);
        model.addAttribute("loggedinUser", user);
        return "ProductManagement";
    }

    @RequestMapping("/editProduct")
    public String editProductModule(@RequestParam int productID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Products> productsList = productService.getAllProducts();
        Products selectedProduct = productService.getProductByProductID(productID);
        List<ProductCategories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productsList);
        model.addAttribute("selectedProduct", selectedProduct);
        model.addAttribute("loggedinUser", user);
        return "ProductManagement";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(@RequestParam int productID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.deleteProductByID(productID);
        model.addAttribute("loggedinUser", user);
        return "redirect:/productManagement";
    }

    @RequestMapping("/addProduct")
    public String addProductModule(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Products> productsList = productService.getAllProducts();
        List<ProductCategories> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productsList);
        model.addAttribute("loggedinUser", user);
        return "ProductManagement";
    }

    @RequestMapping("/createProduct")
    public String createProduct(@RequestParam String name, @RequestParam String description, @RequestParam int quantity,
                             @RequestParam double price, @RequestParam String categoryName, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedinUser", user);

        ProductCategories category = categoriesService.getCategoryName(categoryName);

        Products product = new Products();

        product.setName(name);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategoryID(category.getCategoryID());

        productService.saveProduct(product);

        return "redirect:/productManagement";
    }

    @PostMapping("/saveEdittedProduct")
    public String saveEdittedProduct(@RequestParam String name, @RequestParam String description, @RequestParam int quantity,
                                  @RequestParam double price, @RequestParam String categoryName, @RequestParam int productID){

        ProductCategories category = categoriesService.getCategoryName(categoryName);

        Products product = new Products();

        product.setProductID(productID);
        product.setName(name);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategoryID(category.getCategoryID());

        productService.saveProduct(product);

        return "redirect:/productManagement";
    }
}
