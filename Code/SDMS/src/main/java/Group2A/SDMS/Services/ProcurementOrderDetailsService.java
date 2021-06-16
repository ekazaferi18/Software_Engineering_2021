package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Entities.ProcurementOrderDetails;
import Group2A.SDMS.Repositories.ProcurementOrderDetailsRepository;
import Group2A.SDMS.Repositories.ProcurementOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProcurementOrderDetailsService {

    @Autowired
    private ProcurementOrderDetailsRepository procurementOrderDetailsRepository;

    public ProcurementOrderDetails getProcurementOrderDetailsByID(int pOrderDetailId){
        return procurementOrderDetailsRepository.findBypOrderDetailID(pOrderDetailId);
    }

    public List<ProcurementOrderDetails> getAllProcurementOrders(){
        return procurementOrderDetailsRepository.getAllProcurementOrders();
    }

    public void saveProcurementOrderDetail(ProcurementOrderDetails procurementOrderDetails){ procurementOrderDetailsRepository.save(procurementOrderDetails); }

    public void deleteProcurementOrderDetailByID(int pOrderDetailId){
        procurementOrderDetailsRepository.deleteProcurementOrderByID(pOrderDetailId);
    }


}
