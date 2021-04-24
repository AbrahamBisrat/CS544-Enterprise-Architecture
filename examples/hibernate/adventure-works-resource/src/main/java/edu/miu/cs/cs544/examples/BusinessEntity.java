package edu.miu.cs.cs544.examples;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.BusinessEntity")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BusinessEntity {

	@Id
	@Column(name = "BusinessEntityID")
	@EqualsAndHashCode.Include
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "Person.BusinessEntityContact",
		joinColumns = { @JoinColumn(name = "BusinessEntityID") }, 
		inverseJoinColumns = { @JoinColumn(name = "PersonId")})
	private Person person;
	
//	@ManyToOne
//	@JoinTable(name = "Person.BusinessEntityContact",
//		joinColumns = { @JoinColumn(name = "BusinessEntityID") }, 
//		inverseJoinColumns = { @JoinColumn(name = "ContactTypeID")})
//	private ContactType contactType;
	
	@OneToMany
	@JoinTable(name = "Person.BusinessEntityAddress",
		joinColumns = { @JoinColumn(name = "BusinessEntityID") }, 
		inverseJoinColumns = { @JoinColumn(name = "AddressID")})
	private List<BusinessAddress> addresses;
	
}
