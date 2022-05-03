package edu.miu.cs.cs544.exercise09_1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Subselect;

import java.util.List;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
	@Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany (cascade={CascadeType.PERSIST})//, fetch = FetchType.LAZY)
    @JoinColumn (name="clientid")
//	@BatchSize(size=1000)
//	@Fetch(value = FetchMode.SUBSELECT)
//  @Fetch(FetchMode.JOIN)
    private List<Pet> pets;

	public static Owner create(String name) {
		return new Owner().builder().name(name).build();
	}

}
