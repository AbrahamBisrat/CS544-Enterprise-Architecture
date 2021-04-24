package edu.miu.cs.cs544.examples;

import javax.persistence.Column;
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
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({ 
	@NamedQuery(name = "CountryRegion.All", query = "from CountryRegion") 
})
public class CountryRegion {
	
	@Id
	@Column(name = "CountryRegionCode")
	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
}
