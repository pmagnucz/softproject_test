package hu.uni.miskolc.iit.model;

import java.util.Date;

public class SearchRentRequest {

    private int customerId;
    private int companyId;
    private int vehicleId;
    private Date startDate;
    private Date endDate;

    public SearchRentRequest(int customerId, int companyId, int vehicleId, Date startDate, Date endDate) {
        this.customerId = customerId;
        this.companyId = companyId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
