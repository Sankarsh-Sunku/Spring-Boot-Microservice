package com.dphone.userservice.bean;

public class CustomerBean {
	private int customerId;
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private long mobile;
	private long referralCode;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public long getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(long referralCode) {
		this.referralCode = referralCode;
	}
	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", referralCode="
				+ referralCode + "]";
	}
	
	
}
