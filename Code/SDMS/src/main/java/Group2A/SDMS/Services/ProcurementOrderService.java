package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Entities.Statistics;
import Group2A.SDMS.Repositories.ProcurementOrderDetailsRepository;
import Group2A.SDMS.Repositories.ProcurementOrderRepository;
import Group2A.SDMS.Repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProcurementOrderService {

    @Autowired
    private ProcurementOrderRepository procurementOrderRepository;

    public ProcurementOrder getProcurementOrderByID(int pOrderId){
        return procurementOrderRepository.findBypOrderID(pOrderId);
    }

    public List<ProcurementOrder> getAllProcurementOrders(){
        return procurementOrderRepository.getAllProcurementOrders();
    }

    public void saveProcurementOrder(ProcurementOrder procurementOrder){ procurementOrderRepository.save(procurementOrder); }

    public void deleteProcurementOrderByID(int statsID){
        procurementOrderRepository.deleteProcurementOrderByID(statsID);
    }
}
