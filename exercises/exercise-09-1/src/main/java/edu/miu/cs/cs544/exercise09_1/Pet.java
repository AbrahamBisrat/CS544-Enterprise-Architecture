package edu.miu.cs.cs544.exercise09_1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Pet {
	@Id  
    @GeneratedValue
    private int id;
    private String name;

	public Pet(String name) {
		super();
		this.name = name;
	}

}
