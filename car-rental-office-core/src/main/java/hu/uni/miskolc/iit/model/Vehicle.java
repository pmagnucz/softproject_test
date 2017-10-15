package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 25..
 */
public class Vehicle {
    private String id;
    private VehichleType type;
    private String manufacturer;
    private Date yearOfManufacture;
    private double rentCost;
    private int persons;
    private double performance;
    private String vehicleStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Vehicle() {
    }

    public Vehicle(String id, VehichleType type, String manufacturer, Date yearOfManufacture, double rentCost, int persons, double performance, String vehicleStatus) {
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

        if (Double.compare(vehicle.getRentCost(), getRentCost()) != 0) return false;
        if (getPersons() != vehicle.getPersons()) return false;
        if (Double.compare(vehicle.getPerformance(), getPerformance()) != 0) return false;
        if (getId() != null ? !getId().equals(vehicle.getId()) : vehicle.getId() != null) return false;
        if (getType() != null ? !getType().equals(vehicle.getType()) : vehicle.getType() != null) return false;
        if (getManufacturer() != null ? !getManufacturer().equals(vehicle.getManufacturer()) : vehicle.getManufacturer() != null)
            return false;
        if (getYearOfManufacture() != null ? !getYearOfManufacture().equals(vehicle.getYearOfManufacture()) : vehicle.getYearOfManufacture() != null)
            return false;
        return getVehicleStatus() != null ? getVehicleStatus().equals(vehicle.getVehicleStatus()) : vehicle.getVehicleStatus() == null;

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
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", rentCost=" + rentCost +
                ", persons=" + persons +
                ", performance=" + performance +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                '}';
    }
}
