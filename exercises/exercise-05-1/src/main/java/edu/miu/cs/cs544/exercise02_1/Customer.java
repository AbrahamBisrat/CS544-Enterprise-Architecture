package edu.miu.cs.cs544.exercise02_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @OneToMany
    @JoinColumn(name = "laptop_id")
    private Set<Laptop> laptops;

    public static Customer create(String fName, String lName) {
        return new Customer()
                .builder()
                .firstName(fName)
                .lastName(lName)
                .build();
    }

}
