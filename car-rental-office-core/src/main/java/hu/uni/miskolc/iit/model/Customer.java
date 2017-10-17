package hu.uni.miskolc.iit.model;

/**
 * Created by rozgonyi on 2017. 09. 29..
 */
public class Customer extends User {
    private String userId;
    private int yearOfBirth;
    private String drivingLicenceNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (userId != customer.userId) return false;
        if (yearOfBirth != customer.yearOfBirth) return false;
        return drivingLicenceNumber != null ? drivingLicenceNumber.equals(customer.drivingLicenceNumber) : customer.drivingLicenceNumber == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + yearOfBirth;
        result = 31 * result + (drivingLicenceNumber != null ? drivingLicenceNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                "userId=" + userId +
                ", yearOfBirth=" + yearOfBirth +
                ", drivingLicenceNumber='" + drivingLicenceNumber + '\'' +
                '}';
    }

    public Customer(String userId, int yearOfBirth, String drivingLicenceNumber) {
        this.userId = userId;
        this.yearOfBirth = yearOfBirth;
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public Customer() {
    }
}