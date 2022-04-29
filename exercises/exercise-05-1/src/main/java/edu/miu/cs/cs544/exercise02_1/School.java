package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
//    @ElementCollection
    @JoinTable(name = "school_students_mapping",
            joinColumns = {@JoinColumn(name = "fk_school", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "fk_student", referencedColumnName = "id")} )
    @MapKey(name = "id")
    private Map<Integer, Student> students;

    public static School create(String name) {
        School s = new School();
        s.setName(name);
        return s;
    }

}
