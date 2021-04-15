package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Person.Person")
@SecondaryTables({
	@SecondaryTable(name = "Person.Password", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "BusinessEntityID", referencedColumnName = "BusinessEntityID") 
	}),
	@SecondaryTable(name = "Person.EmailAddress", pkJoinColumns = {
			@PrimaryKeyJoinColumn(name = "BusinessEntityID", referencedColumnName = "BusinessEntityID") 
	})
})
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

	@Id
	@Column(name = "BusinessEntityID")
	private Integer id;
	
	private String personType;
	
	private String title;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;

	private String suffix;

	private LocalDate modifiedDate;
	
	@Column(table = "Person.Password")
	private String passwordHash;
	
	@Column(table = "Person.EmailAddress")
	private String emailAddress;

}
