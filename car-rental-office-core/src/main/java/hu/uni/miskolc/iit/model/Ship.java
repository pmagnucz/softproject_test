package hu.uni.miskolc.iit.model;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */

public class Ship extends Vehicle{
    private String Id;
    private String ProductName;
    private double Length;
    private int NumberOfPeopleBeingTransported;
    private boolean withTrailer;

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void setId(String id) {
        Id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getLength() {
        return Length;
    }

    public void setLength(double length) {
        Length = length;
    }

    public int getNumberOfPeopleBeingTransported() {
        return NumberOfPeopleBeingTransported;
    }

    public void setNumberOfPeopleBeingTransported(int numberOfPeopleBeingTransported) {
        NumberOfPeopleBeingTransported = numberOfPeopleBeingTransported;
    }

    public boolean getWithTrailer() {
        return withTrailer;
    }

    public void setWithTrailer(boolean withTrailer) {
        this.withTrailer = withTrailer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ship ship = (Ship) o;

        if (Double.compare(ship.Length, Length) != 0) return false;
        if (NumberOfPeopleBeingTransported != ship.NumberOfPeopleBeingTransported) return false;
        if (withTrailer != ship.withTrailer) return false;
        if (Id != null ? !Id.equals(ship.Id) : ship.Id != null) return false;
        return ProductName != null ? ProductName.equals(ship.ProductName) : ship.ProductName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (Id != null ? Id.hashCode() : 0);
        result = 31 * result + (ProductName != null ? ProductName.hashCode() : 0);
        temp = Double.doubleToLongBits(Length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + NumberOfPeopleBeingTransported;
        result = 31 * result + (withTrailer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "Id='" + Id + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", Length=" + Length +
                ", NumberOfPeopleBeingTransported=" + NumberOfPeopleBeingTransported +
                ", withTrailer=" + withTrailer +
                '}';
    }
}
