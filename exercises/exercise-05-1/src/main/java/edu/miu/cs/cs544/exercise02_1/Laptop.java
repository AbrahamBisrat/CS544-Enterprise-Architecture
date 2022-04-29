package edu.miu.cs.cs544.exercise02_1;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ISBN")
    private String brand;
    private String type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public static Laptop create(String brand, String type) {
        return new Laptop()
                .builder()
                .brand(brand)
                .type(type)
                .build();
    }

}
