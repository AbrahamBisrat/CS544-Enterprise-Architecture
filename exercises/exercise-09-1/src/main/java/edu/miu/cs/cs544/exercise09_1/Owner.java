package edu.miu.cs.cs544.exercise09_1;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Subselect;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Data
@Entity
@NoArgsConstructor
public class Owner {
	@Id  
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany (cascade={CascadeType.PERSIST})
    @JoinColumn (name="clientid")
//	@BatchSize(size=100)
//	@Fetch(value = FetchMode.SUBSELECT)
    @Fetch(FetchMode.JOIN)
    private List<Pet> pets;

	public Owner(String name) {
		super();
		this.name = name;
	}

}
