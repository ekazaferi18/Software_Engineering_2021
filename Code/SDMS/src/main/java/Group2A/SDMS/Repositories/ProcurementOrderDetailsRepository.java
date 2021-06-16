package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Entities.ProcurementOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcurementOrderDetailsRepository extends JpaRepository<ProcurementOrderDetails, Integer> {

    ProcurementOrderDetails findBypOrderDetailID(int pOrderDetailID);

    @Query("FROM ProcurementOrderDetails WHERE deleted = false")
    List<ProcurementOrderDetails> getAllProcurementOrders();

    @Modifying
    @Query("update ProcurementOrderDetails set deleted = true where pOrderID = :pOrderDetailID")
    void deleteProcurementOrderByID(@Param("pOrderDetailID") int pOrderDetailID);

    @Modifying
    @Query(value = "update ProcurementOrderDetails set productID = :productID, pOrderID = :pOrderID, quantity = :quantity, " +
            "pricePerUnit = :pricePerUnit, totalPrice = :totalPrice where pOrderDetailID = :pOrderDetailID", nativeQuery = true)
    void editProcurementOrder(@Param("productID") int productID, @Param("pOrderID") int pOrderID, @Param("quantity") int quantity,
                              @Param("pricePerUnit") double pricePerUnit, @Param("totalPrice") double totalPrice, @Param("pOrderDetailID") int pOrderDetailID);
}
