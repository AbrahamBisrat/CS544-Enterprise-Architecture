package edu.miu.cs.cs544.examples;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Person.StateProvince")
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StateProvince {
	
	@Id
	@Column(name = "StateProvinceID")
	@EqualsAndHashCode.Include
	private Long id;
	
	private String stateProvinceCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryRegionCode")
	private CountryRegion countryRegion;
	
	private String name;
	
	@Embedded
	private AuditData auditData;
	
}
