package hu.uni.miskolc.iit.entity;

import javax.persistence.*;

@Entity
@Table(name = RentEntity.TABLE_NAME)
public class RentEntity {
    protected static final String TABLE_NAME = "rents";

    private static final String ID = "ID";
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String VEHICLE_ID = "VEHICLE_ID";
    private static final String START_DATE = "START_DATE";
    private static final String END_DATE = "END_DATE";
    private static final String DURATION_EXTENDABLE = "DURATION_EXTENDABLE";
    private static final String EXTENDED_HOURS = "EXTENDED_HOURS";
    private static final String KM_USED = "KM_USED";
    private static final String DAY_FEE = "DAY_FEE";
    private static final String KM_FEE = "KM_FEE";
    private static final String OTHER_FEE = "OTHER_FEE";
    private static final String TOTAL_FEE = "TOTAL_FEE";
    private static final String PAID = "PAID";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = CUSTOMER_ID)
    private Long customerId;
    @Column(name = COMPANY_ID)
    private Long companyId;
    @Column(name = VEHICLE_ID)
    private Long vehicleId;

    @Column(name = START_DATE)
    private String startDate;

    @Column(name = END_DATE)
    private String endDate;

    @Column(name = DURATION_EXTENDABLE)
    private String durationExtendable;

    @Column(name = EXTENDED_HOURS)
    private String extendedHours;
    @Column(name = KM_USED)
    private String kmUsed;

    @Column(name = DAY_FEE)
    private String dayFee;
    @Column(name = KM_FEE)
    private String kmFee;
    @Column(name = OTHER_FEE)
    private String otherFee;
    @Column(name = TOTAL_FEE)
    private String totalFee;

    @Column(name = PAID)
    private String paid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDurationExtendable() {
        return durationExtendable;
    }

    public void setDurationExtendable(String durationExtendable) {
        this.durationExtendable = durationExtendable;
    }

    public String getExtendedHours() {
        return extendedHours;
    }

    public void setExtendedHours(String extendedHours) {
        this.extendedHours = extendedHours;
    }

    public String getKmUsed() {
        return kmUsed;
    }

    public void setKmUsed(String kmUsed) {
        this.kmUsed = kmUsed;
    }

    public String getDayFee() {
        return dayFee;
    }

    public void setDayFee(String dayFee) {
        this.dayFee = dayFee;
    }

    public String getKmFee() {
        return kmFee;
    }

    public void setKmFee(String kmFee) {
        this.kmFee = kmFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentEntity that = (RentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (vehicleId != null ? !vehicleId.equals(that.vehicleId) : that.vehicleId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (durationExtendable != null ? !durationExtendable.equals(that.durationExtendable) : that.durationExtendable != null)
            return false;
        if (extendedHours != null ? !extendedHours.equals(that.extendedHours) : that.extendedHours != null)
            return false;
        if (kmUsed != null ? !kmUsed.equals(that.kmUsed) : that.kmUsed != null) return false;
        if (dayFee != null ? !dayFee.equals(that.dayFee) : that.dayFee != null) return false;
        if (kmFee != null ? !kmFee.equals(that.kmFee) : that.kmFee != null) return false;
        if (otherFee != null ? !otherFee.equals(that.otherFee) : that.otherFee != null) return false;
        if (totalFee != null ? !totalFee.equals(that.totalFee) : that.totalFee != null) return false;
        return paid != null ? paid.equals(that.paid) : that.paid == null;
    }

    @Override
    public String toString() {
        return "RentEntity{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", durationExtendable='" + durationExtendable + '\'' +
                ", extendedHours='" + extendedHours + '\'' +
                ", kmUsed='" + kmUsed + '\'' +
                ", dayFee='" + dayFee + '\'' +
                ", kmFee='" + kmFee + '\'' +
                ", otherFee='" + otherFee + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", paid='" + paid + '\'' +
                '}';
    }
}