package uni.miskolc.iit.model.rent;

import java.util.Date;

public class Rent {

	private long id;
	
	private long vehicleId;
	private long userId;
	
	private Date rentStartDate;
	private Date rentEndDate;
	
	private int daysUsed;
	private int kmUsed;
	
	private int vehicleDailyFee;
	private int vehicleKmFee;
	
	public Rent(long id, long vehicleId, long userId, Date rentStartDate, Date rentEndDate, int daysUsed, int kmUsed,
			int vehicleDailyFee, int vehicleKmFee) {
		super();
		this.id = id;
		this.vehicleId = vehicleId;
		this.userId = userId;
		this.rentStartDate = rentStartDate;
		this.rentEndDate = rentEndDate;
		this.daysUsed = daysUsed;
		this.kmUsed = kmUsed;
		this.vehicleDailyFee = vehicleDailyFee;
		this.vehicleKmFee = vehicleKmFee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public int getDaysUsed() {
		return daysUsed;
	}

	public void setDaysUsed(int daysUsed) {
		this.daysUsed = daysUsed;
	}

	public int getKmUsed() {
		return kmUsed;
	}

	public void setKmUsed(int kmUsed) {
		this.kmUsed = kmUsed;
	}

	public int getVehicleDailyFee() {
		return vehicleDailyFee;
	}

	public void setVehicleDailyFee(int vehicleDailyFee) {
		this.vehicleDailyFee = vehicleDailyFee;
	}

	public int getVehicleKmFee() {
		return vehicleKmFee;
	}

	public void setVehicleKmFee(int vehicleKmFee) {
		this.vehicleKmFee = vehicleKmFee;
	}		
	
}
