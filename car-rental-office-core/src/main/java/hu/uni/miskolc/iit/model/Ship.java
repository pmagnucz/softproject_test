package hu.uni.miskolc.iit.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */

public class Ship extends Vehicle{
    private String shipId;
    private double length;
    private boolean withTrailer;

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

    public  Ship(){

    }

    public Ship(String shipId, double length, boolean withTrailer) {
        this.shipId = shipId;
        this.length = length;
        this.withTrailer = withTrailer;
    }

    public Ship(Long id, VehicleType type, String manufacturer, LocalDate yearOfManufacture, double rentCost, int persons, double performance, VehicleStatusType vehicleStatus, String shipId, double length, boolean withTrailer) {
        super(id, type, manufacturer, yearOfManufacture, rentCost, persons, performance, vehicleStatus);
        this.shipId = shipId;
        this.length = length;
        this.withTrailer = withTrailer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ship ship = (Ship) o;

        if (Double.compare(ship.length, length) != 0) return false;
        if (withTrailer != ship.withTrailer) return false;
        return shipId != null ? shipId.equals(ship.shipId) : ship.shipId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (shipId != null ? shipId.hashCode() : 0);
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (withTrailer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipId='" + shipId + '\'' +
                ", length=" + length +
                ", withTrailer=" + withTrailer +
                '}';
    }
}
