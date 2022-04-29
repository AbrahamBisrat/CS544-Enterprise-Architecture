package edu.miu.cs.cs544.exercise02_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    public static OrderLine create(int quantity) {
        return new OrderLine()
                .builder()
                .quantity(quantity)
                .build();
    }

}
