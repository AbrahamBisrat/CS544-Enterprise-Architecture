package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;
    private String firstName;

    @ManyToMany
    @JoinTable( name = "Student_Course",
        joinColumns = { @JoinColumn (name = "student_id")},
        inverseJoinColumns = { @JoinColumn (name = "course_id")})
    private Collection<Course> courses = new ArrayList<>();

}
