package edu.miu.cs.cs544;

public class Account {
	double balance;
	long accountNumber;
	
	public Account(long accountNumber) {
		this.balance = 0;
		this.accountNumber = accountNumber;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
}
