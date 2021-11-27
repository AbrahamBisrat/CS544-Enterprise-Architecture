package edu.miu.cs.cs544.examples.bank.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	
	@Id
	private String name;

	public Customer() {
		super();
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

}
