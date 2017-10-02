package hu.uni.miskolc.iit.entity;

import javax.persistence.*;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Entity
@Table(name = CustomerEntity.TABLE_NAME)
public class CustomerEntity {
    protected static final String TABLE_NAME = "customers";
    private static final String ID = "ID";
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String DRIVING_LICENSE_NUMBER = "DRIVING_LICENSE_NUMBER";
    private static final String YEAR_OF_BIRTH = "YEAR_OF_BIRTH";
    private static final String ADDRESS = "ADDRESS";
    private static final String USERNAME = "USERNAME";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = CUSTOMER_ID)
    private String customerId;

    @Column(name = DRIVING_LICENSE_NUMBER)
    private String drivingLicenseNumber;

    @Column(name = YEAR_OF_BIRTH)
    private String yearOfBirth;

    @Column(name = USERNAME)
    private String userName;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = PHONE_NUMBER)
    private long phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
