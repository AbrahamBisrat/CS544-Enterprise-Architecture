package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name = "employeeId")
    private Collection<Employee> employeeList = new ArrayList<>();

}
