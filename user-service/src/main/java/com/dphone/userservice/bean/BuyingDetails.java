package com.dphone.userservice.bean;

import java.sql.Date;
import java.time.LocalDate;

public class BuyingDetails {
	
	private int purchaseId;
	private int customerId;
	private int userId;
	private LocalDate purchaseDate;
	private String productName;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	@Override
	public String toString() {
		return "BuyingDetails [purchaseId=" + purchaseId + ", customerId=" + customerId + ", userId=" + userId
				+ ", purchaseDate=" + purchaseDate + ", productName=" + productName + ", status=" + status + "]";
	}
	
	
	
}
