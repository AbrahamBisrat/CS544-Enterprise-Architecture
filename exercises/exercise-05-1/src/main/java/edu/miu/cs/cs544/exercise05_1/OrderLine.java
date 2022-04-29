package edu.miu.cs.cs544.exercise05_1;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "orderline_product")
    private Product product;

    public static OrderLine create(int quantity, Product product) {
        return new OrderLine()
                .builder()
                .quantity(quantity)
                .product(product)
                .build();
    }

}
