package edu.miu.cs.cs544.exercise13_1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
	private String name;
	private String email;
	private Address address;
}
