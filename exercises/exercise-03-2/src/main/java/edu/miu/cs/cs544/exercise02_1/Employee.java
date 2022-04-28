package edu.miu.cs.cs544.exercise02_1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_number")
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

}

/**
 *
 * 1. Bidirectional OneToMany => Department - Employee
 * 2. Optional Unidirectional ManyToOne => Book - Publisher
 * 3. Bidirectional ManyToOne => Student - Course
 * 4. Unidirectional OneToMany => Customer - Reservation
 * 5. Unidirectional ManyToOne => Reservation - Book
 * 6. Bidirectional ManyToOne => Employee - Office
 *
 */
