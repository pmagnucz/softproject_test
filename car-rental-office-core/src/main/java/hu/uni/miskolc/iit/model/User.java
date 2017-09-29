package hu.uni.miskolc.iit.model;

public class User {
	private String userName;
	private String address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
		return address != null ? address.equals(user.address) : user.address == null;
	}

	@Override
	public int hashCode() {
		int result = userName != null ? userName.hashCode() : 0;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
