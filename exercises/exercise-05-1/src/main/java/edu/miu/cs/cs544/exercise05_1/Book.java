package edu.miu.cs.cs544.exercise05_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Product{

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    public static Book create(String title, String name, String desc) {
        Book book = new Book();
        book.setTitle(title);
        book.setName(name);
        book.setDescription(desc);

        return book;
    }

}
