package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 25..
 */
public class Car extends Vehicle {
    private String plateNumber;
    private String vehicleIdentificationNumber;
    private boolean drawBar;

    public Car() {

    }

    public Car(Long id, VehicleType type, String manufacturer, Date yearOfManufacture, double rentCost, int persons, double performance, VehicleStatusType vehicleStatus, String plateNumber, String vehicleIdentificationNumber, boolean drawBar) {
        super(id, type, manufacturer, yearOfManufacture, rentCost, persons, performance, vehicleStatus);
        this.plateNumber = plateNumber;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.drawBar = drawBar;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (isDrawBar() != car.isDrawBar()) return false;
        if (getPlateNumber() != null ? !getPlateNumber().equals(car.getPlateNumber()) : car.getPlateNumber() != null)
            return false;
        return getVehicleIdentificationNumber() != null ? getVehicleIdentificationNumber().equals(car.getVehicleIdentificationNumber()) : car.getVehicleIdentificationNumber() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPlateNumber() != null ? getPlateNumber().hashCode() : 0);
        result = 31 * result + (getVehicleIdentificationNumber() != null ? getVehicleIdentificationNumber().hashCode() : 0);
        result = 31 * result + (isDrawBar() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber='" + plateNumber + '\'' +
                ", vehicleIdentificationNumber='" + vehicleIdentificationNumber + '\'' +
                ", drawBar=" + drawBar +
                '}';
    }
}
