package edu.miu.cs.cs544.exercise02_1;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flightnumber")
    private String flightNumber;
    private String from;
    private String to;
    private LocalDate date;

}
