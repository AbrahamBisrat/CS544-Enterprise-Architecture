package edu.miu.cs.cs544.exercise02_1.Assig;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @Column
    private String title;
    @Column(name = "ISBN")
    private String iSBN;
    @Column
    private String author;
    @Column
    private double price;
    @Column(name = "published_date")
    private LocalDate publishDate;

    // Book Factory
    public static Book create (String title, String iSBN, String author, double price, LocalDate publishDate) {
        Book book = new Book();
        book.title = title;
        book.iSBN = iSBN;
        book.author = author;
        book.price = price;
        book.publishDate = publishDate;
        return book;
    }

}
