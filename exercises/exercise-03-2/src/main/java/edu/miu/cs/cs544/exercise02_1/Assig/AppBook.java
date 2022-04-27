package edu.miu.cs.cs544.exercise02_1.Assig;

import edu.miu.cs.cs544.exercise02_1.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class AppBook {

    public static void main(String[] args){
        // TODO
        partOne();
        partTwo();
        partThree();
        partFour();
    }

    private static void partOne() {
        apply(session -> {
            Book book1 = Book.create("Effective Java", "1234", "Joshua Bloch", 50, LocalDate.now());
            Book book2 = Book.create("Refactoring", "1231", "Martin Fowler", 34, LocalDate.now());
            Book book3 = Book.create("Design Patterns", "4345", "The Gang", 45, LocalDate.now());

            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
        });
    }

    private static void partTwo() {
        apply(session -> {
            Query<Book> bookQuery = session.createQuery("from Book", Book.class);
            bookQuery.getResultList().forEach(System.out::println);
        });
    }

    private static void partThree() {
        apply(session -> {
            Query<Book> bookQuery = session.createQuery("from Book", Book.class);
            List<Book> bookList = bookQuery.getResultList();

            Book book1 = bookList.get(0);
            session.persist(book1);

            Book book2 = bookList.get(1);
            book2.setAuthor(book2.getAuthor() + " updated");
            session.persist(book2);

            Book book3 = bookList.get(2);
            session.delete(book3);
        });
    }

    private static void partFour() {
        partTwo();
    }


    private static void apply(Consumer<Session> consumer) {
        try {
            final Session session = HibernateUtils.getSession(Arrays.asList(Book.class));

            Transaction txn = session.beginTransaction();

            consumer.accept(session);

            txn.commit();

            session.close();
        } catch (HibernateException e) {
//            txn.rollback
            e.printStackTrace();
        }
    }

}

/**
 * Open a session
 *  Create 3 books save them to the database
 *  Close the session
 *  Open a session
 *
 *  Retrieve all books and output them to the console
 *  Close the session
 *  Open a session
 *
 *  Retrieve a book from the database and change its title and price
 *  Delete a book (not the one that was just updated)
 *  Close the session
 *
 *  Open a session
 *  Retrieve all books and output them to the console
 *  Close the session
 */