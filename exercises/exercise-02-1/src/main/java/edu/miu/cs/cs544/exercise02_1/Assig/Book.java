package edu.miu.cs.cs544.exercise02_1.Assig;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
@ToString
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id;
    private String title;
    private String iSBN;
    private String author;
    private double price;
    private Date publishDate;

    public Book(String title, String iSBN, String author, double price, Date publishDate) {
        this.title = title;
        this.iSBN = iSBN;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Double.compare(book.price, price) == 0 && Objects.equals(title, book.title) && Objects.equals(iSBN, book.iSBN) && Objects.equals(author, book.author) && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, iSBN, author, price, publishDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
