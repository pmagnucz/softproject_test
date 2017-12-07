package hu.uni.miskolc.iit.dao.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.uni.miskolc.iit.model.Rent;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class RentDaoBean {
    private Long id;

    private Long customerId;
    private Long companyId;
    private Long vehicleId;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Budapest")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Budapest")
    private Date endDate;

    private boolean durationExtendable;
    private int extendedHours;
    private int kmUsed;
    private double dayFee;
    private double kmFee;
    private double otherFee;
    private double totalFee;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentDaoBean rent = (RentDaoBean) o;

        if (id != rent.id) return false;
        if (customerId != rent.customerId) return false;
        if (companyId != rent.companyId) return false;
        if (vehicleId != rent.vehicleId) return false;
        if (durationExtendable != rent.durationExtendable) return false;
        if (extendedHours != rent.extendedHours) return false;
        if (kmUsed != rent.kmUsed) return false;
        if (Double.compare(rent.dayFee, dayFee) != 0) return false;
        if (Double.compare(rent.kmFee, kmFee) != 0) return false;
        if (Double.compare(rent.otherFee, otherFee) != 0) return false;
        if (Double.compare(rent.totalFee, totalFee) != 0) return false;
        if (paid != rent.paid) return false;
        if (!startDate.equals(rent.startDate)) return false;
        return endDate.equals(rent.endDate);
    }

    @Override
    public String toString() {
        return "Rent{" +
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

    public Rent extract()
    {
        Rent rent= new Rent();
        rent.setId(this.getId());
        rent.setCustomerId(this.getCustomerId());
        rent.setCompanyId(this.getCompanyId());
        rent.setVehicleId(this.getVehicleId());
        rent.setStartDate(this.getStartDate());
        rent.setEndDate(this.getEndDate());
        rent.setDurationExtendable(this.durationExtendable);
        rent.setExtendedHours(this.getExtendedHours());
        rent.setKmUsed(this.getKmUsed());
        rent.setDayFee(this.getDayFee());
        rent.setKmFee(this.getKmFee());
        rent.setOtherFee(this.getOtherFee());
        rent.setTotalFee(this.getTotalFee());
        rent.setPaid(this.isPaid());
        return rent;
    }
}
