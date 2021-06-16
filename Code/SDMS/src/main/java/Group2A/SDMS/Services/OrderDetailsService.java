package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.OrderDetails;
import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getAllOrderDetailsByOrderID(int orderID){
        return orderDetailsRepository.getAllOrderDetailsByOrderID(orderID);
    }

    public OrderDetails getOrderDetailsByID(int orderID){
        return orderDetailsRepository.findByOrderDetailID(orderID);
    }

    public List<OrderDetails> getAllOrderDetails(){
        return orderDetailsRepository.getAllOrderDetails();
    }

    public void saveOrderDetail(OrderDetails orderDetails){ orderDetailsRepository.save(orderDetails); }

    public void deleteOrderDetailByID(int orderID){
        orderDetailsRepository.deleteOrderDetailsByID(orderID);
    }
}
