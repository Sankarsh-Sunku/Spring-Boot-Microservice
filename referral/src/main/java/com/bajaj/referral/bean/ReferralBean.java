package com.bajaj.referral.bean;

import java.time.LocalDate;

public class ReferralBean {

	private int referralId;
	private String referralFirstName;
	private String referralLastName;
	private long mobile;
	private String email;
	private LocalDate dateOfReferral;
	private int referralPoints;
	private String status;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	private int userId;
	public int getReferralId() {
		return referralId;
	}
	public void setReferralId(int referralId) {
		this.referralId = referralId;
	}
	public String getReferralFirstName() {
		return referralFirstName;
	}
	public void setReferralFirstName(String referralFirstName) {
		this.referralFirstName = referralFirstName;
	}
	public String getReferralLastName() {
		return referralLastName;
	}
	public void setReferralLastName(String referralLastName) {
		this.referralLastName = referralLastName;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfReferral() {
		return dateOfReferral;
	}
	public void setDateOfReferral(LocalDate dateOfReferral) {
		this.dateOfReferral = dateOfReferral;
	}
	public int getReferralPoints() {
		return referralPoints;
	}
	public void setReferralPoints(int referralPoints) {
		this.referralPoints = referralPoints;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ReferralBean [referralId=" + referralId + ", referralFirstName=" + referralFirstName
				+ ", referralLastName=" + referralLastName + ", mobile=" + mobile + ", email=" + email
				+ ", dateOfReferral=" + dateOfReferral + ", referralPoints=" + referralPoints + ", status=" + status
				+ ", userId=" + userId + "]";
	}
	

}