package uni.miskolc.hu.model.payment;

public class Payment {

	private long id;
	
	private long userId;
	private long vehicleId;
	private long rentId;
	
	private int dailyFee;
	private int kmFee;
	private int damageFee;
	private int totalFee;
	
	private boolean paid;

	public Payment(long id, long userId,long rentId, long vehicleId, int dailyFee, int kmFee, int damageFee,boolean paid) {
		super();
		this.id = id;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.rentId = rentId;
		this.dailyFee = dailyFee;
		this.kmFee = kmFee;
		this.damageFee = damageFee;
		this.totalFee = dailyFee + kmFee + damageFee;
		this.paid = paid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getRentId() {
		return rentId;
	}

	public void setRentId(long rentId) {
		this.rentId = rentId;
	}

	public int getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(int dailyFee) {
		this.dailyFee = dailyFee;
		this.totalFee = dailyFee + kmFee + damageFee;
	}

	public int getKmFee() {
		return kmFee;
	}

	public void setKmFee(int kmFee) {
		this.kmFee = kmFee;
		this.totalFee = dailyFee + kmFee + damageFee;
	}

	public int getDamageFee() {
		return damageFee;
	}

	public void setDamageFee(int damageFee) {
		this.damageFee = damageFee;
		this.totalFee = dailyFee + kmFee + damageFee;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
}