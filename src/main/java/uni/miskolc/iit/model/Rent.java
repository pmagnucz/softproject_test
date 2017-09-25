package uni.miskolc.iit.model;

import java.util.Date;

public class Rent {

	private long id;
	
	private AbstractUser user;
	private Vehicle vehicle;	
	
	private Date rentStartDate;
	private Date rentEndDate;

	private boolean durationExtendable;

	private double dayFee;
	private double kmFee;
	private double damageFee;
	private double totalFee;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser user) {
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
	public double getDamageFee() {
		return damageFee;
	}
	public void setDamageFee(double damageFee) {
		this.damageFee = damageFee;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(damageFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dayFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (durationExtendable ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		temp = Double.doubleToLongBits(kmFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rentEndDate == null) ? 0 : rentEndDate.hashCode());
		result = prime * result + ((rentStartDate == null) ? 0 : rentStartDate.hashCode());
		temp = Double.doubleToLongBits(totalFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		if (Double.doubleToLongBits(damageFee) != Double.doubleToLongBits(other.damageFee))
			return false;
		if (Double.doubleToLongBits(dayFee) != Double.doubleToLongBits(other.dayFee))
			return false;
		if (durationExtendable != other.durationExtendable)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(kmFee) != Double.doubleToLongBits(other.kmFee))
			return false;
		if (rentEndDate == null) {
			if (other.rentEndDate != null)
				return false;
		} else if (!rentEndDate.equals(other.rentEndDate))
			return false;
		if (rentStartDate == null) {
			if (other.rentStartDate != null)
				return false;
		} else if (!rentStartDate.equals(other.rentStartDate))
			return false;
		if (Double.doubleToLongBits(totalFee) != Double.doubleToLongBits(other.totalFee))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Rent [id=" + id + ", vehicle=" + vehicle + ", rentStartDate=" + rentStartDate + ", rentEndDate="
				+ rentEndDate + ", durationExtendable=" + durationExtendable + ", dayFee=" + dayFee + ", kmFee=" + kmFee
				+ ", damageFee=" + damageFee + ", totalFee=" + totalFee + "]";
	}
	
}
