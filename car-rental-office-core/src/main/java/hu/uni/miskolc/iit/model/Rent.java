package hu.uni.miskolc.iit.model;

import java.util.Date;

public class Rent {

	private User user;
	private Vehicle vehicle;	
	
	private Date rentStartDate;
	private Date rentEndDate;

	private boolean durationExtendable;

	private int extendedHours;
	private int kmUsed;

	private double dayFee;
	private double kmFee;
	private double otherFee;
	private double totalFee;

	private boolean paid;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(Date rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public Date getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(Date rentEndDate) {
		this.rentEndDate = rentEndDate;
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
				"user=" + user +
				", vehicle=" + vehicle +
				", rentStartDate=" + rentStartDate +
				", rentEndDate=" + rentEndDate +
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
