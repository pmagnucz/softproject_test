package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 25..
 */
public class Vehicle {
    private Long id;
    private VehicleType type;
    private String manufacturer;
    private Date yearOfManufacture;
    private double rentCost;
    private int persons;
    private double performance;
    private VehicleStatusType vehicleStatus;

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

    public Vehicle(){

    }

    public Vehicle(Long id, VehicleType type, String manufacturer, Date yearOfManufacture, double rentCost, int persons, double performance, VehicleStatusType vehicleStatus) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.rentCost = rentCost;
        this.persons = persons;
        this.performance = performance;
        this.vehicleStatus = vehicleStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (Double.compare(vehicle.rentCost, rentCost) != 0) return false;
        if (persons != vehicle.persons) return false;
        if (Double.compare(vehicle.performance, performance) != 0) return false;
        if (type != vehicle.type) return false;
        if (manufacturer != null ? !manufacturer.equals(vehicle.manufacturer) : vehicle.manufacturer != null)
            return false;
        if (yearOfManufacture != null ? !yearOfManufacture.equals(vehicle.yearOfManufacture) : vehicle.yearOfManufacture != null)
            return false;
        return vehicleStatus == vehicle.vehicleStatus;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        result = 31 * result + (getYearOfManufacture() != null ? getYearOfManufacture().hashCode() : 0);
        temp = Double.doubleToLongBits(getRentCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getPersons();
        temp = Double.doubleToLongBits(getPerformance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getVehicleStatus() != null ? getVehicleStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", rentCost=" + rentCost +
                ", persons=" + persons +
                ", performance=" + performance +
                ", vehicleStatus=" + vehicleStatus +
                '}';
    }
}
