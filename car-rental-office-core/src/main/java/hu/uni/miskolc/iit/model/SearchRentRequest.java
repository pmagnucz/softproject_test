package hu.uni.miskolc.iit.model;

import java.time.LocalDate;
import java.util.Date;

public class SearchRentRequest {

    private int customerId;
    private int companyId;
    private int vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;

    public SearchRentRequest(int customerId, int companyId, int vehicleId, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
