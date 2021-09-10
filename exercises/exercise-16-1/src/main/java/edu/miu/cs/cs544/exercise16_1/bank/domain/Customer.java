package edu.miu.cs.cs544.exercise16_1.bank.domain;

import javax.persistence.Entity;

@Entity
public class Customer {
	
	
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
