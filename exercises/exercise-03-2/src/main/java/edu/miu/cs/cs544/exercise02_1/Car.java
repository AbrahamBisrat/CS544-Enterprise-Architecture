package edu.miu.cs.cs544.exercise02_1;

import edu.miu.cs.cs544.exercise02_1.Assig.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String brand;
	private String year;
	private double price;

	@ManyToOne( cascade = CascadeType.PERSIST )
	private Owner owner;

	public static Car create(String brand, String year, double price) {
		return new Car()
			.builder()
				.brand(brand)
				.year(year)
				.price(price)
			.build();
	}
}
