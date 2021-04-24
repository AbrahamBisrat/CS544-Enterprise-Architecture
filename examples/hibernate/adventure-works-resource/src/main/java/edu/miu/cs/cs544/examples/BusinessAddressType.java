package edu.miu.cs.cs544.examples;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.AddressType")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BusinessAddressType {

	@Id
	@Column(name = "AddressTypeID")
	@EqualsAndHashCode.Include
	private Integer type;
	
	private String name;
	
	@Embedded
	private AuditData auditData;

}
