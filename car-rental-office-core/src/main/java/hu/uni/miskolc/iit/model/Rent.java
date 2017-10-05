package hu.uni.miskolc.iit.model;

import java.util.Date;

public class Rent {

	private Long id;

	private Long customerId;
	private Long companyId;
	private Long vehicleId;

	private Date startDate;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Rent rent = (Rent) o;

		if (durationExtendable != rent.durationExtendable) return false;
		if (extendedHours != rent.extendedHours) return false;
		if (kmUsed != rent.kmUsed) return false;
		if (Double.compare(rent.dayFee, dayFee) != 0) return false;
		if (Double.compare(rent.kmFee, kmFee) != 0) return false;
		if (Double.compare(rent.otherFee, otherFee) != 0) return false;
		if (Double.compare(rent.totalFee, totalFee) != 0) return false;
		if (paid != rent.paid) return false;
		if (!id.equals(rent.id)) return false;
		if (!customerId.equals(rent.customerId)) return false;
		if (!companyId.equals(rent.companyId)) return false;
		if (!vehicleId.equals(rent.vehicleId)) return false;
		if (!startDate.equals(rent.startDate)) return false;
		return endDate.equals(rent.endDate);
	}

}