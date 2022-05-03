package edu.miu.cs.cs544.exercise09_1;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	@Id  
    @GeneratedValue
    private int id;
    private String name;

	public static Pet create(String name) {
		return new Pet().builder().name(name).build();
	}

}
