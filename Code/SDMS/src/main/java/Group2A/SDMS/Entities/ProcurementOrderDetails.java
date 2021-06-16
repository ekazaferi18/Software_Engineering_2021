package Group2A.SDMS.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class ProcurementOrderDetails {

    @Id
    private int pOrderDetailID;
    private int pOrderID;
    private int productId;
    private int quantity;
    private double pricePerUnit;
    private double totalPrice;
    private boolean deleted;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public int getpOrderDetailID() {
        return pOrderDetailID;
    }

    public void setpOrderDetailID(int pOrderDetailID) {
        this.pOrderDetailID = pOrderDetailID;
    }

    public int getpOrderID() {
        return pOrderID;
    }

    public void setpOrderID(int pOrderID) {
        this.pOrderID = pOrderID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreated_date() {
        return createdDate;
    }

    public void setCreated_date(Timestamp created_date) {
        this.createdDate = created_date;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updated_date) {
        this.updatedDate = updated_date;
    }
}
