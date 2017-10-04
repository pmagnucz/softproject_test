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
    private String representative;

    @Column(name = FINANCIAL_ADDRESS)
    private String billingAddress;

    @Column(name = USERNAME)
    private String userName;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

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

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", companyId='" + companyId + '\'' +
                ", representative='" + representative + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCustomerId() != null ? !getCustomerId().equals(that.getCustomerId()) : that.getCustomerId() != null)
            return false;
        if (getDrivingLicenseNumber() != null ? !getDrivingLicenseNumber().equals(that.getDrivingLicenseNumber()) : that.getDrivingLicenseNumber() != null)
            return false;
        if (getYearOfBirth() != null ? !getYearOfBirth().equals(that.getYearOfBirth()) : that.getYearOfBirth() != null)
            return false;
        if (getCompanyId() != null ? !getCompanyId().equals(that.getCompanyId()) : that.getCompanyId() != null)
            return false;
        if (getRepresentative() != null ? !getRepresentative().equals(that.getRepresentative()) : that.getRepresentative() != null)
            return false;
        if (getBillingAddress() != null ? !getBillingAddress().equals(that.getBillingAddress()) : that.getBillingAddress() != null)
            return false;
        if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() == null;

    }

}