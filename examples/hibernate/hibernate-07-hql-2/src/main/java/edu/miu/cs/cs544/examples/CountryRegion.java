package edu.miu.cs.cs544.examples;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({ 
	@NamedQuery(name = "Country.All", query = "from CountryRegion") 
})
public class CountryRegion {
	
	@Id
	private String countryRegionCode;
	
	private String name;
	
	private LocalDate modifiedDate;
	
}
