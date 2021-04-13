package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.StateProvince")
@NoArgsConstructor @Getter @Setter 
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StateProvince {
	
	@Id
	@Column(name = "StateProvinceID")
	private Integer id;
	
	private String stateProvinceCode;
	
	@ManyToOne
	@JoinColumn(name = "countryRegionCode")
	private CountryRegion countryRegion;
	
	private boolean onlyStateProvinceFlag;
	
	private String name;

	private LocalDate modifiedDate;
	
}
