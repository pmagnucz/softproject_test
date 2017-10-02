package hu.uni.miskolc.iit.entity;

import javax.persistence.*;

/**
 * Created by pmagnucz on 2017. 10. 02..
 */
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity {
    protected static final String TABLE_NAME = "users";

    private static final String ID = "ID";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String BUSINESS_REPRESENTATIVE = "BUSINESS_REPRESENTATIVE";
    private static final String ADDRESS = "ADDRESS";
    private static final String FINANCIAL_ADDRESS = "FINANCIAL_ADDRESS";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String DRIVING_LICENSE_NUMBER = "DRIVING_LICENSE_NUMBER";
    private static final String YEAR_OF_BIRTH = "YEAR_OF_BIRTH";
    private static final String USERNAME = "USERNAME";

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

    @Column(name = COMPANY_ID)
    private String companyId;

    @Column(name = BUSINESS_REPRESENTATIVE)
    private Long representative;

    @Column(name = FINANCIAL_ADDRESS)
    private String billingAddress;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getRepresentative() {
        return representative;
    }

    public void setRepresentative(Long representative) {
        this.representative = representative;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
