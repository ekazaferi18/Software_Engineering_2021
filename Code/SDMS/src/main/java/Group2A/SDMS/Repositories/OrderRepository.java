package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    Orders findByOrderID(int orderID);

    @Query("FROM Orders WHERE deleted = false")
    List<Orders> getAllOrders();

    @Query(value = "select * from Orders order by orderID desc limit 1", nativeQuery = true)
    Orders getLastOrder();

    @Modifying
    @Query("update Orders set deleted = true where orderID = :orderID")
    void deleteOrderByID(@Param("orderID") int orderID);

    @Modifying
    @Query(value = "update Orders set paymnetType = :paymentType, clientID = :clientID, sellerID = :sellerID," +
            "total = :total, pointsEarned = :pointsEarned where orderID = :orderID", nativeQuery = true)
    void editOrder(@Param("paymentType") String paymentType, @Param("clientID") int clientID,
                   @Param("sellerID") int sellerID, @Param("total") double total, @Param("pointsEarned") String pointsEarned,
                  @Param("orderID") int orderID);
}
