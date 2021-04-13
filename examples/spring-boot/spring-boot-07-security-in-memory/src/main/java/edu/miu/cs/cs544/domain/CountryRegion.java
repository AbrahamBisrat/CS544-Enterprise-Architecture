package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class CountryRegion {
	
	@Id
	@Column(name = "CountryRegionCode")
	private String code;
	
	private String name;
	
	private LocalDate modifiedDate;
	
}
