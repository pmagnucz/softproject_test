package hu.uni.miskolc.iit.entity;

import javax.persistence.*;
import java.util.Date;

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
    private Date startDate;

    @Column(name = END_DATE)
    private Date endDate;

    @Column(name = DURATION_EXTENDABLE)
    private boolean durationExtendable;

    @Column(name = EXTENDED_HOURS)
    private int extendedHours;
    @Column(name = KM_USED)
    private int kmUsed;

    @Column(name = DAY_FEE)
    private double dayFee;
    @Column(name = KM_FEE)
    private double kmFee;
    @Column(name = OTHER_FEE)
    private double otherFee;
    @Column(name = TOTAL_FEE)
    private double totalFee;

    @Column(name = PAID)
    private boolean paid;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isDurationExtendable() {
        return durationExtendable;
    }

    public void setDurationExtendable(boolean durationExtendable) {
        this.durationExtendable = durationExtendable;
    }

    public int getExtendedHours() {
        return extendedHours;
    }

    public void setExtendedHours(int extendedHours) {
        this.extendedHours = extendedHours;
    }

    public int getKmUsed() {
        return kmUsed;
    }

    public void setKmUsed(int kmUsed) {
        this.kmUsed = kmUsed;
    }

    public double getDayFee() {
        return dayFee;
    }

    public void setDayFee(double dayFee) {
        this.dayFee = dayFee;
    }

    public double getKmFee() {
        return kmFee;
    }

    public void setKmFee(double kmFee) {
        this.kmFee = kmFee;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "RentEntity{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", companyId=" + companyId +
                ", vehicleId=" + vehicleId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", durationExtendable=" + durationExtendable +
                ", extendedHours=" + extendedHours +
                ", kmUsed=" + kmUsed +
                ", dayFee=" + dayFee +
                ", kmFee=" + kmFee +
                ", otherFee=" + otherFee +
                ", totalFee=" + totalFee +
                ", paid=" + paid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentEntity that = (RentEntity) o;

        if (durationExtendable != that.durationExtendable) return false;
        if (extendedHours != that.extendedHours) return false;
        if (kmUsed != that.kmUsed) return false;
        if (Double.compare(that.dayFee, dayFee) != 0) return false;
        if (Double.compare(that.kmFee, kmFee) != 0) return false;
        if (Double.compare(that.otherFee, otherFee) != 0) return false;
        if (Double.compare(that.totalFee, totalFee) != 0) return false;
        if (paid != that.paid) return false;
        if (!id.equals(that.id)) return false;
        if (!customerId.equals(that.customerId)) return false;
        if (!companyId.equals(that.companyId)) return false;
        if (!vehicleId.equals(that.vehicleId)) return false;
        if (!startDate.equals(that.startDate)) return false;
        return endDate.equals(that.endDate);
    }

}