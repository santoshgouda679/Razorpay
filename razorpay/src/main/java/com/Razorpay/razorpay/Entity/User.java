package com.Razorpay.razorpay.Entity;

import lombok.Data;

@Data
public class User { 
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private int amount;
}
