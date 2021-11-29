package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity
@Immutable
public class Country {
	
	@Id
	@Column(name = "COUNTRY_ID")
	private Integer id;
	
	@Column(name = "COUNTRY")
	private String name;
	
	@Column(name = "LAST_UPDATE")
	private LocalDate lastUpdateDate;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_ID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<City> cities = new ArrayList<>();
	
}
