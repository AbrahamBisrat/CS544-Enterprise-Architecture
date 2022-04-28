package edu.miu.cs.cs544.exercise02_1;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomnumber")
    private int roomNumber;
    private String building;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
