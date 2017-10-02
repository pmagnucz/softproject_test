package hu.uni.miskolc.iit.entity;

import javax.persistence.*;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Entity
@Table(name = CompanyEntity.TABLE_NAME)
public class CompanyEntity {
    protected static final String TABLE_NAME = "companies";

    private static final String ID = "ID";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String BUSINESS_REPRESENTATIVE = "BUSINESS_REPRESENTATIVE";
    private static final String ADDRESS = "ADDRESS";
    private static final String FINANCIAL_ADDRESS = "FINANCIAL_ADDRESS";
    private static final String USERNAME = "USERNAME";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = COMPANY_ID)
    private String companyId;

    @OneToOne
    @JoinColumn(name = BUSINESS_REPRESENTATIVE)
    private CustomerEntity representative;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CustomerEntity getBusinessRepresentative() {
        return representative;
    }

    public void setBusinessRepresentative(CustomerEntity representative) {
        this.representative = representative;
    }

    public String getFinancialAddress() {
        return billingAddress;
    }

    public void setFinancialAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
