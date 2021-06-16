package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Entities.Products;
import Group2A.SDMS.Services.OrdersService;
import Group2A.SDMS.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    private List<Orders> orders;

    @RequestMapping("/orderManagement")
    public String getAllUsers(Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("loggedinUser", user);
        return "OrderManagement";
    }

    @RequestMapping("/viewOrders")
    public String getAllOrders(Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("loggedinUser", user);
        return "ViewOrders";
    }

    @RequestMapping("/createOrderView")
    public String createOrdersView(Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("loggedinUser", user);
        return "CreateOrdersView";
    }

    @RequestMapping("/editOrder")
    public String editUserModule(@RequestParam int orderID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Orders selectedOrder = ordersService.getOrdersByID(orderID);
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("selectedOrder", selectedOrder);
        model.addAttribute("loggedinUser", user);
        return "OrderManagement";
    }

    @RequestMapping("/deleteOrder")
    public String deleteUser(@RequestParam int orderID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ordersService.deleteOrderByID(orderID);
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("loggedinUser", user);
        return "redirect:/orderManagement";
    }

    @RequestMapping("/addOrder")
    public String addUserModule(Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("loggedinUser", user);
        return "OrderManagement";
    }

    @RequestMapping("/createOrder")
    public String createUser(@RequestParam int sellerID, Model model){
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Products> products = productService.getAllProducts();

        orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("products", orders);
        model.addAttribute("loggedinUser", user);

        Orders order = new Orders();

        order.setSellerID(sellerID);

        ordersService.saveOrder(order);

        return "redirect:/createOrderDetail";
    }
//
//    @PostMapping("/saveEdittedCategory")
//    public String saveEdittedUser(@RequestParam int categoryID, @RequestParam String name,
//                                  @RequestParam String description){
//
//        ProductCategories category = new ProductCategories();
//
//        category.setName(name);
//        category.setCategoryID(categoryID);
//        category.setDescription(description);
//
//        categoryService.saveCategory(category);
//
//        return "redirect:/orderManagement";
//    }
}
