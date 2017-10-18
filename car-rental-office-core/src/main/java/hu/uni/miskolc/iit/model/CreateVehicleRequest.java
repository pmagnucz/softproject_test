package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by rozgo on 2017. 10. 18..
 */
public class CreateVehicleRequest {
    private int id;
    private VehichleType type;
    private String manufacturer;
    private Date yearOfManufacture;
    private double rentCost;
    private int persons;
    private double performance;
    private VehicleStatusType vehicleStatus;

    private String plateNumber;
    private String vehicleIdentificationNumber;
    private boolean drawBar;

    private String shipId;
    private double length;
    private boolean withTrailer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehichleType getType() {
        return type;
    }

    public void setType(VehichleType type) {
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

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public VehicleStatusType getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatusType vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }

    public boolean isDrawBar() {
        return drawBar;
    }

    public void setDrawBar(boolean drawBar) {
        this.drawBar = drawBar;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isWithTrailer() {
        return withTrailer;
    }

    public void setWithTrailer(boolean withTrailer) {
        this.withTrailer = withTrailer;
    }
}
