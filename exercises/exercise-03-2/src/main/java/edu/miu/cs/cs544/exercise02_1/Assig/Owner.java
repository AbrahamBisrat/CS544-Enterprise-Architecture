package edu.miu.cs.cs544.exercise02_1.Assig;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    public static Owner create(String name, String address){
        return new Owner()
                .builder()
                    .name(name)
                    .address(address)
                .build();
    }
}
