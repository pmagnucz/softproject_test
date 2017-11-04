package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class SearchVehicleRequest {
    private VehicleType type;
    private String manufacturer;
    private Date yearOfManufacture;
    private double rentCost;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Date yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    public SearchVehicleRequest(VehicleType type, String manufacturer, Date yearOfManufacture, double rentCost) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.rentCost = rentCost;
    }

    public SearchVehicleRequest() {
    }
}
