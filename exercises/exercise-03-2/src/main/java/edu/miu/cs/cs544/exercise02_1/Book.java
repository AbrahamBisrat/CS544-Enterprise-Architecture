package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ISBN")
    private String iSBN;
    private String title;
    private String author;

}
