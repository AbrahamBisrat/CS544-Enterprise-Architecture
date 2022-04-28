package edu.miu.cs.cs544.exercise02_1;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinTable(name = "publisher_book")
    private Book book;

}
