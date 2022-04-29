package edu.miu.cs.cs544.exercise02_1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int id;
    private LocalDate date;

    public static Order create(LocalDate date) {
        return new Order()
                .builder()
                .date(date)
                .build();
    }

}
