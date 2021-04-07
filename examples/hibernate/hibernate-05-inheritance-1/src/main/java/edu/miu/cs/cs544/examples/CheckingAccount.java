package edu.miu.cs.cs544.examples;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("checking")
public class CheckingAccount extends Account {
	private double interestRate;

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(double balance, double interestRate) {
		super(balance);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
}
