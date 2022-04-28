package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int courseNumber;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Collection<Student> students = new ArrayList<>();

}
