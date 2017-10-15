package hu.uni.miskolc.iit.model;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */

public class Ship extends Vehicle{
    private String id;
    private double length;
    private boolean withTrailer;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public Ship(){

    }

    public Ship(String id, VehichleType type, String manufacturer, Date yearOfManufacture, double rentCost, int persons, double performance, String vehicleStatus, String id1, double length, boolean withTrailer) {
        super(id, type, manufacturer, yearOfManufacture, rentCost, persons, performance, vehicleStatus);
        this.id = id1;
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
        return id != null ? id.equals(ship.id) : ship.id == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (withTrailer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", length=" + length +
                ", withTrailer=" + withTrailer +
                '}';
    }
}
