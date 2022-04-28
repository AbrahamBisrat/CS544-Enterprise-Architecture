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
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flightnumber")
    private String flightNumber;
    @Column(name = "departure")
    private String from;
    @Column(name = "destination")
    private String to;
    private LocalDate date;

    public static Flight create(String fNum, String from, String to, LocalDate date) {
        return new Flight()
                .builder()
                .flightNumber(fNum)
                .from(from)
                .to(to)
                .date(date)
                .build();
    }

}
