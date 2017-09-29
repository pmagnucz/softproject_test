package hu.uni.miskolc.iit.model;

/**
 * Created by rozgonyi on 2017. 09. 29..
 */
public class Company extends User {
    private int companyId;
    private String billingAddress;
    private Customer representative;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
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

        if (companyId != company.companyId) return false;
        if (billingAddress != null ? !billingAddress.equals(company.billingAddress) : company.billingAddress != null)
            return false;
        return representative != null ? representative.equals(company.representative) : company.representative == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + companyId;
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (representative != null ? representative.hashCode() : 0);
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
}
