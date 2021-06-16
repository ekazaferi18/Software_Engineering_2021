package Group2A.SDMS.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ProcurementOrder {

    @Id
    private int pOrderID;
    private String sellerName;
    private double total;
    private boolean deleted;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public int getpOrderID() {
        return pOrderID;
    }

    public void setpOrderID(int pOrderID) {
        this.pOrderID = pOrderID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp created_date) {
        this.createdDate = created_date;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updated_date) {
        this.updatedDate = updated_date;
    }
}
