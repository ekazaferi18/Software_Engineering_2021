package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders getLastOrder(){
        return orderRepository.getLastOrder();
    }

    public Orders getOrdersByID(int orderID){
        return orderRepository.findByOrderID(orderID);
    }

    public List<Orders> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public void saveOrder(Orders order){ orderRepository.save(order); }

    public void deleteOrderByID(int orderID){
        orderRepository.deleteOrderByID(orderID);
    }
}