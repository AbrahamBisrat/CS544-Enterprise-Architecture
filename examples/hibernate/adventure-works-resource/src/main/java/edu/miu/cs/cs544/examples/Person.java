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
@Table(name = "Person.Person")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

	@Id
	@Column(name = "BusinessEntityID")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "PersonType")
	private String personType;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name="FirstName")
	private String firstName;
	
	
	@Column(name="MiddleName")
	private String middleName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Embedded
	private AuditData auditData;
	
}
