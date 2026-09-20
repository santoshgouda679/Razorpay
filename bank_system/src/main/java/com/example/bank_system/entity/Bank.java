package com.example.bank_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * NOTE: previously this class had @Data (Lombok) AND a full set of manually
 * written getters/setters. That's redundant - Lombok already generates
 * them. Keeping both risks them drifting out of sync, so the manual ones
 * were removed and only @Data is kept.
 *
 * Validation annotations were added so bad input (blank names, negative
 * balances, etc.) is rejected with a 400 instead of silently saved or
 * blowing up later.
 */
@Data
@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Account holder name is required")
	private String accountHolderName;

	@Positive(message = "Account number must be a positive number")
	private long accountNumber;

	@NotBlank(message = "Account type is required")
	private String accountType;

	@PositiveOrZero(message = "Balance cannot be negative")
	private double balance;

	@NotBlank(message = "Branch is required")
	private String branch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	

	
}
