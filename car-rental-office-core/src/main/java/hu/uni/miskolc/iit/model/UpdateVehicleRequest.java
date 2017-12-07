package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 11. 03..
 */
public class UpdateVehicleRequest {
    private Long id;
    private VehicleType type;
    private String manufacturer;
    private Date yearOfManufacture;
    private double rentCost;
    private int persons;
    private double performance;
    private VehicleStatusType vehicleStatus;

    private boolean car;
    private String plateNumber;
    private String vehicleIdentificationNumber;
    private boolean drawBar;

    private boolean ship;
    private String shipId;
    private double length;
    private boolean withTrailer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isCar() {
        return car;
    }

    public void setCar(boolean car) {
        this.car = car;
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

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
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
