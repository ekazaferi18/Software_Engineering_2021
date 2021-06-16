package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.Orders;
import Group2A.SDMS.Entities.ProcurementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProcurementOrderRepository extends JpaRepository<ProcurementOrder, Integer> {

    ProcurementOrder findBypOrderID(int pOrderDetailID);

    @Query("FROM ProcurementOrder WHERE deleted = false")
    List<ProcurementOrder> getAllProcurementOrders();

    @Modifying
    @Query("update ProcurementOrder set deleted = true where pOrderID = :pOrderID")
    void deleteProcurementOrderByID(@Param("pOrderID") int pOrderID);

    @Modifying
    @Query(value = "update ProcurementOrder set sellerName = :sellerName, total = :total where pOrderID = :pOrderID", nativeQuery = true)
    void editProcurementOrder(@Param("sellerName") String sellerName, @Param("total") double total, @Param("pOrderID") int pOrderID);
}
