package hu.uni.miskolc.iit.model;

/**
 * Created by pmagnucz on 2017. 11. 03..
 */
public class UpdateUserRequest {
    private Long id;
    private String userName;
    private String address;
    private String phoneNumber;
    private boolean company;
    private String companyId;
    private String billingAddress;
    private Customer representative;
    private boolean customer;
    private String userId;
    private int yearOfBirth;
    private String drivingLicenceNumber;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Customer getRepresentative() {
        return representative;
    }

    public void setRepresentative(Customer representative) {
        this.representative = representative;
    }

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

    public boolean isCompany() {
        return company;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }
}
