package hu.uni.miskolc.iit.dao.beans;

import hu.uni.miskolc.iit.model.*;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class VehicleDaoBean {
    private Long id;
    private String plateNumber;
    private String vehicleIdentificationNumber;
    private boolean drawBar;
    private String shipId;
    private double length;
    private boolean withTrailer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleDaoBean that = (VehicleDaoBean) o;

        if (isDrawBar() != that.isDrawBar()) return false;
        if (Double.compare(that.getLength(), getLength()) != 0) return false;
        if (isWithTrailer() != that.isWithTrailer()) return false;
        if (Double.compare(that.getRentCost(), getRentCost()) != 0) return false;
        if (getPersons() != that.getPersons()) return false;
        if (Double.compare(that.getPerformance(), getPerformance()) != 0) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPlateNumber() != null ? !getPlateNumber().equals(that.getPlateNumber()) : that.getPlateNumber() != null)
            return false;
        if (getVehicleIdentificationNumber() != null ? !getVehicleIdentificationNumber().equals(that.getVehicleIdentificationNumber()) : that.getVehicleIdentificationNumber() != null)
            return false;
        if (getShipId() != null ? !getShipId().equals(that.getShipId()) : that.getShipId() != null) return false;
        if (getType() != that.getType()) return false;
        if (getManufacturer() != null ? !getManufacturer().equals(that.getManufacturer()) : that.getManufacturer() != null)
            return false;
        if (getYearOfManufacture() != null ? !getYearOfManufacture().equals(that.getYearOfManufacture()) : that.getYearOfManufacture() != null)
            return false;
        return getVehicleStatus() == that.getVehicleStatus();

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPlateNumber() != null ? getPlateNumber().hashCode() : 0);
        result = 31 * result + (getVehicleIdentificationNumber() != null ? getVehicleIdentificationNumber().hashCode() : 0);
        result = 31 * result + (isDrawBar() ? 1 : 0);
        result = 31 * result + (getShipId() != null ? getShipId().hashCode() : 0);
        temp = Double.doubleToLongBits(getLength());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isWithTrailer() ? 1 : 0);
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

    public Vehicle extract() {
        if (this.getType() == VehicleType.CAR)
        {
            Car result = new Car();
            result.setPlateNumber(this.getPlateNumber());
            result.setVehicleIdentificationNumber(this.getVehicleIdentificationNumber());
            result.setDrawBar(this.isDrawBar());
            result.setId(this.getId());
            result.setType(this.getType());
            result.setManufacturer(this.getManufacturer());
            result.setYearOfManufacture(this.getYearOfManufacture());
            result.setRentCost(this.getRentCost());
            result.setPersons(this.getPersons());
            result.setPerformance(this.getPerformance());
            result.setVehicleStatus(this.getVehicleStatus());
            return result;
        } else {
            Ship result = new Ship();
            result.setShipId(this.getShipId());
            result.setLength(this.getLength());
            result.setWithTrailer(this.isWithTrailer());
            result.setId(this.getId());
            result.setType(this.getType());
            result.setManufacturer(this.getManufacturer());
            result.setYearOfManufacture(this.getYearOfManufacture());
            result.setRentCost(this.getRentCost());
            result.setPersons(this.getPersons());
            result.setPerformance(this.getPerformance());
            result.setVehicleStatus(getVehicleStatus());
            return result;
        }
    }
}
