package hu.uni.miskolc.iit.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class SearchVehicleRequest {
    private VehicleType type;
    private String manufacturer;
    private LocalDate yearOfManufacture;
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

    public LocalDate getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(LocalDate yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getRentCost() {
        return rentCost;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    public SearchVehicleRequest(){

    }

    public SearchVehicleRequest(VehicleType type, String manufacturer, LocalDate yearOfManufacture, double rentCost) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.rentCost = rentCost;
    }
}
