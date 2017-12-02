package hu.uni.miskolc.iit.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Rent {

	private Long id;

	private Long customerId;
	private Long companyId;
	private Long vehicleId;

	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Budapest")
	private LocalDate startDate;

	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Budapest")
	private LocalDate endDate;

	private boolean durationExtendable;

	private int extendedHours;
	private int kmUsed;

	private double dayFee;
	private double kmFee;
	private double otherFee;
	private double totalFee;

	private boolean paid;

	public Rent() {

	}

	public Rent(Long id, Long customerId, Long companyId, Long vehicleId, LocalDate startDate, LocalDate endDate, boolean durationExtendable, int extendedHours, int kmUsed, double dayFee, double kmFee, double otherFee, double totalFee, boolean paid) {
		this.id = id;
		this.customerId = customerId;
		this.companyId = companyId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationExtendable = durationExtendable;
		this.extendedHours = extendedHours;
		this.kmUsed = kmUsed;
		this.dayFee = dayFee;
		this.kmFee = kmFee;
		this.otherFee = otherFee;
		this.totalFee = totalFee;
		this.paid = paid;
	}

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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

		Rent rent = (Rent) o;

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
}