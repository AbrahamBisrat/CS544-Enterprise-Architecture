package edu.miu.cs.cs544.exercise16_1.bank.domain;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntry {
	
	private Date date;
	private double amount;
	private String description;
	private String fromAccountNumber;
	private String fromPersonName;

}
