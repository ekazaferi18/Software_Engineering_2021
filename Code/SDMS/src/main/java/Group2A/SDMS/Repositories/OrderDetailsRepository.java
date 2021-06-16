package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.OrderDetails;
import Group2A.SDMS.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    OrderDetails findByOrderDetailID(int orderDetailsID);

    @Query("FROM OrderDetails WHERE deleted = false")
    List<OrderDetails> getAllOrderDetails();

    @Query("FROM OrderDetails WHERE deleted = false and orderID = :orderID")
    List<OrderDetails> getAllOrderDetailsByOrderID(@Param("orderID") int orderID);

    @Modifying
    @Query("update OrderDetails set deleted = true where orderDetailID = :orderDetailID")
    void deleteOrderDetailsByID(@Param("orderDetailID") int orderDetailID);

    @Modifying
    @Query(value = "update OrderDetails set productID = :productID, orderID = :orderID, quantity = :quantity, pricePerUnit = :pricePerUnit," +
            "totalPrice = :totalPrice where orderDetailID = :orderDetailID", nativeQuery = true)
    void editOrderDetails(@Param("productID") int productID, @Param("orderID") int orderID, @Param("quantity") int quantity,
                  @Param("pricePerUnit") double pricePerUnit, @Param("totalPrice") double totalPrice,
                  @Param("orderDetailID") int orderDetailID);
}
