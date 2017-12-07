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

        if (getYearOfBirth() != customer.getYearOfBirth()) return false;
        if (getUserId() != null ? !getUserId().equals(customer.getUserId()) : customer.getUserId() != null)
            return false;
        return getDrivingLicenceNumber() != null ? getDrivingLicenceNumber().equals(customer.getDrivingLicenceNumber()) : customer.getDrivingLicenceNumber() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + getYearOfBirth();
        result = 31 * result + (getDrivingLicenceNumber() != null ? getDrivingLicenceNumber().hashCode() : 0);
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