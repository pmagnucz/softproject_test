package hu.uni.miskolc.iit.entity;

import javax.persistence.*;

/**
 * Created by rozgonyi on 2017. 10. 06..
 */

@Entity
@Table(name = VehicleEntity.TABLE__NAME)
public class VehicleEntity {
    protected static final String TABLE__NAME = "vehicles";

    private static final String ID = "ID";
    private static final String PLATE_NUMBER = "PLATE_NUMBER";
    private static final String VEHICLE_IDENTIFICATION_NUMBER = "VEHICLE_IDENTIFICATION_NUMBER";
    private static final String DRAW_BAR = "DRAW_BAR";
    private static final String SHIP_ID = "SHIP_ID";
    private static final String LENGTH = "LENGTH";
    private static final String WITH_TRAILER = "WITH_TRAILER";
    private static final String VEHICLE_ID = "VEHICLE_ID";
    private static final String VEHICLE_TYPE = "VEHICLE_TYPE";
    private static final String MANUFACTURER = "MANUFACTURER";
    private static final String YEAR_OF_MANUFACTURE = "YEAR_OF_MANUFACTURE";
    private static final String RENT_COST = "RENT_COST";
    private static final String PERSONS = "PERSONS";
    private static final String PERFORMANCE = "PERFORMANCE";
    private static final String VEHICLE_STATUS = "VEHICLE_STATUS";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = PLATE_NUMBER)
    private String plateNumber;

    @Column(name = VEHICLE_IDENTIFICATION_NUMBER)
    private String vehicleIdentificationNumber;

    @Column(name = DRAW_BAR)
    private String drawBar;

    @Column(name = SHIP_ID)
    private String shipId;

    @Column(name = LENGTH)
    private String Length;

    @Column(name = WITH_TRAILER)
    private String withTrailer;

    @Column(name = VEHICLE_ID)
    private String vehicleId;

    @Column(name = VEHICLE_TYPE)
    private String type;

    @Column(name = MANUFACTURER)
    private String manufacturer;

    @Column(name = YEAR_OF_MANUFACTURE)
    private String yearOfManufacture;

    @Column(name = RENT_COST)
    private String rentCost;

    @Column(name = PERSONS)
    private String persons;

    @Column(name = PERFORMANCE)
    private String performance;

    @Column(name = VEHICLE_STATUS)
    private String vehicleStatus;

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

    public String getDrawBar() {
        return drawBar;
    }

    public void setDrawBar(String drawBar) {
        this.drawBar = drawBar;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getWithTrailer() {
        return withTrailer;
    }

    public void setWithTrailer(String withTrailer) {
        this.withTrailer = withTrailer;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getRentCost() {
        return rentCost;
    }

    public void setRentCost(String rentCost) {
        this.rentCost = rentCost;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleEntity that = (VehicleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (plateNumber != null ? !plateNumber.equals(that.plateNumber) : that.plateNumber != null) return false;
        if (vehicleIdentificationNumber != null ? !vehicleIdentificationNumber.equals(that.vehicleIdentificationNumber) : that.vehicleIdentificationNumber != null)
            return false;
        if (drawBar != null ? !drawBar.equals(that.drawBar) : that.drawBar != null) return false;
        if (shipId != null ? !shipId.equals(that.shipId) : that.shipId != null) return false;
        if (Length != null ? !Length.equals(that.Length) : that.Length != null) return false;
        if (withTrailer != null ? !withTrailer.equals(that.withTrailer) : that.withTrailer != null) return false;
        if (vehicleId != null ? !vehicleId.equals(that.vehicleId) : that.vehicleId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (yearOfManufacture != null ? !yearOfManufacture.equals(that.yearOfManufacture) : that.yearOfManufacture != null)
            return false;
        if (rentCost != null ? !rentCost.equals(that.rentCost) : that.rentCost != null) return false;
        if (persons != null ? !persons.equals(that.persons) : that.persons != null) return false;
        if (performance != null ? !performance.equals(that.performance) : that.performance != null) return false;
        return vehicleStatus != null ? vehicleStatus.equals(that.vehicleStatus) : that.vehicleStatus == null;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", vehicleIdentificationNumber='" + vehicleIdentificationNumber + '\'' +
                ", drawBar='" + drawBar + '\'' +
                ", shipId='" + shipId + '\'' +
                ", Length='" + Length + '\'' +
                ", withTrailer='" + withTrailer + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture='" + yearOfManufacture + '\'' +
                ", rentCost='" + rentCost + '\'' +
                ", persons='" + persons + '\'' +
                ", performance='" + performance + '\'' +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                '}';
    }
}