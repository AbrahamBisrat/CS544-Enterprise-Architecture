package edu.miu.cs.cs544.exercise05_1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Order> orders = new ArrayList<>();

    public static Customer create(String fName, String lName) {
        return new Customer().builder().firstName(fName).lastName(lName).build();
    }

}
