package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Double balance = 0.0;

}
