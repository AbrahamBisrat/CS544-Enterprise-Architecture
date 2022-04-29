package edu.miu.cs.cs544.exercise05_1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int id;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<OrderLine> oderLines = new ArrayList<>();

    public static Order create(LocalDate date) {
        return new Order()
                .builder()
                .date(date)
                .build();
    }

}
