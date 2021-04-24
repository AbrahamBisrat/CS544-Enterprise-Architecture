package edu.miu.cs.cs544.examples;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.Address")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BusinessAddress {

	@Id
	@Column(name = "AddressID")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "AddressLine1")
	private String address1;
	
	@Column(name = "AddressLine2")
	private String address2;
	
	@Column(name="City")
	private String city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StateProvinceID")
	private StateProvince stateProvince;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "Person.BusinessEntityAddress",
		joinColumns = { @JoinColumn(name = "AddressID") }, 
		inverseJoinColumns = { @JoinColumn(name = "AddressTypeID")})
	private BusinessAddressType type;
	
	private String postalCode;
	
	@Embedded
	private AuditData auditData;
	
}
