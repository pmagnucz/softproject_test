package hu.uni.miskolc.iit.model;

/**
 * Created by rozgonyi on 2017. 09. 29..
 */
public class Company extends User {
    private String companyId;
    private String billingAddress;
    private Customer representative;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;

        if (getCompanyId() != null ? !getCompanyId().equals(company.getCompanyId()) : company.getCompanyId() != null)
            return false;
        if (getBillingAddress() != null ? !getBillingAddress().equals(company.getBillingAddress()) : company.getBillingAddress() != null)
            return false;
        return getRepresentative() != null ? getRepresentative().equals(company.getRepresentative()) : company.getRepresentative() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCompanyId() != null ? getCompanyId().hashCode() : 0);
        result = 31 * result + (getBillingAddress() != null ? getBillingAddress().hashCode() : 0);
        result = 31 * result + (getRepresentative() != null ? getRepresentative().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", billingAddress='" + billingAddress + '\'' +
                ", representative=" + representative +
                '}';
    }

    public Company(String companyId, String billingAddress, Customer representative) {
        this.companyId = companyId;
        this.billingAddress = billingAddress;
        this.representative = representative;
    }

    public Company() {
    }
}