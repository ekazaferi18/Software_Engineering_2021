package Group2A.SDMS.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Statistics {

    @Id
    private int statsID;
    private Timestamp month;
    private int year;
    private double wages;
    private double pOrdersTotal;
    private double revenue;
    private double income;
    private boolean deleted;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public int getStatsID() {
        return statsID;
    }

    public void setStatsID(int statsID) {
        this.statsID = statsID;
    }

    public Timestamp getMonth() {
        return month;
    }

    public void setMonth(Timestamp month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public double getpOrdersTotal() {
        return pOrdersTotal;
    }

    public void setpOrdersTotal(double pOrdersTotal) {
        this.pOrdersTotal = pOrdersTotal;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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

    public void setCreated_date(Timestamp created_date) {
        this.createdDate = created_date;
    }

    public Timestamp getUpdated_date() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updated_date) {
        this.updatedDate = updated_date;
    }
}
