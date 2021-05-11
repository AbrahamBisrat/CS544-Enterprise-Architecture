package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.CountryRegion")
@NoArgsConstructor @Getter @Setter 
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {
	
	@Id
	@Column(name = "CountryRegionCode")
	private String id;
	
	private String name;
	
	private LocalDate modifiedDate;
	
	@Transient
	private Integer population = 1000000;
	
}
