package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.OrderDetails;
import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Services.OrderDetailsService;
import Group2A.SDMS.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrdersService ordersService;

    private List<OrderDetails> orderDetails;

    @RequestMapping("/viewOrdersDetails")
    public String getAllOrderDetails(@RequestParam int orderID, Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDetails = orderDetailsService.getAllOrderDetailsByOrderID(orderID);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("loggedinUser", user);
        return "OrderManagement";
    }

    @RequestMapping("/createOrderDetail")
    public String createOrderDetails(@RequestParam int orderID, Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDetails = orderDetailsService.getAllOrderDetailsByOrderID(orderID);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("loggedinUser", user);
        return "OrderManagement";
    }

    @RequestMapping("/createOrderDetailview")
    public String createOrderDetailsView(@RequestParam int orderID, Model model) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDetails = orderDetailsService.getAllOrderDetailsByOrderID(orderID);
        Orders order = ordersService.getLastOrder();
        model.addAttribute("currentOrderID", order.getOrderID());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("loggedinUser", user);
        return "CreateOrderDetailsView";
    }
}
