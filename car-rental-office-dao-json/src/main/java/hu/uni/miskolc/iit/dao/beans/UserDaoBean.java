package hu.uni.miskolc.iit.dao.beans;

import hu.uni.miskolc.iit.model.Company;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class UserDaoBean {
    private Long id;
    private String customerId;
    private String drivingLicenseNumber;
    private String companyId;
    private String representative;
    private String billingAddress;
    private String userName;
    private String address;
    private String phoneNumber;
    private int yearOfBirth;

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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
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

        UserDaoBean that = (UserDaoBean) o;

        if (getYearOfBirth() != that.getYearOfBirth()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCustomerId() != null ? !getCustomerId().equals(that.getCustomerId()) : that.getCustomerId() != null)
            return false;
        if (getDrivingLicenseNumber() != null ? !getDrivingLicenseNumber().equals(that.getDrivingLicenseNumber()) : that.getDrivingLicenseNumber() != null)
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

    public User extract() {

        if (this.getCustomerId()!= null && !this.getCustomerId().isEmpty())
        {
            Customer customer = new Customer();
            customer.setUserId(this.getCustomerId());
            customer.setYearOfBirth(this.getYearOfBirth());
            customer.setDrivingLicenceNumber(this.getDrivingLicenseNumber());
            customer.setId(this.getId());
            customer.setPhoneNumber(this.getPhoneNumber());
            customer.setUserName(this.getUserName());
            customer.setAddress(this.getAddress());

            return customer;
        } else
        {
            Company company = new Company();
            company.setCompanyId(this.getCompanyId());
            company.setBillingAddress(this.getBillingAddress());
            company.setRepresentative(new Customer());
            company.setId(this.getId());
            company.setPhoneNumber(this.getPhoneNumber());
            company.setUserName(this.getUserName());
            company.setAddress(this.getAddress());

            return company;
        }
    }
}
