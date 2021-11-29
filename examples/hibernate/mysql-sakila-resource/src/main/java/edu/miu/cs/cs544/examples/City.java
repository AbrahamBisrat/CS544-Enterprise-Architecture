package edu.miu.cs.cs544.examples;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity
@Immutable
public class City {
	
	@Id
	@Column(name = "CITY_ID")
	private Integer id;
	
	@Column(name = "CITY")
	private String name;
	
	@Column(name = "LAST_UPDATE")
	private LocalDate lastUpdateDate;
	
}
