package edu.miu.cs.cs544.exercise02_1.Assig;

import edu.miu.cs.cs544.exercise02_1.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AppBook {

    private static final SessionFactory sessionFactory;

    static {
        // add more Classes if working with in this class;
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
    }

    public static void main(String[] args){

        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // create and persist the Books
            Book b1 = new Book("book 1", "1", "author 1", 234, new Date());
            session.persist(b1);

            Book b2 = new Book("book 2", "2", "author 2", 234, new Date());
            session.persist(b2);

            Book b3 = new Book("book 3", "3", "author 3", 234, new Date());
            session.persist(b3);

            tx.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            tx.rollback();
            e.printStackTrace();
        } finally { // cleanup
            if (session != null)
                session.close();
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            List<Book> bookList = session.createQuery("from Book ", Book.class).list();

            for(Book eachBook : bookList)
                System.out.println("eachBook = " + eachBook);

            tx.commit();// careful here about introducing side effects
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }


        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            List<Book> bookList = session.createQuery("from Book ", Book.class).list();

            Book b1 = bookList.get(0);
            b1.setAuthor("you");
            session.persist(b1);

            Book b2 = bookList.get(1);
            b2.setTitle("some title");
            session.persist(b2);

            Book b3 = bookList.get(2);
            System.out.println("\n\n\nDeleting things here");
            System.out.println(b3 + "\n\n\n");
            session.delete(b3);

            tx.commit();// careful here about introducing side effects
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            List<Book> bookList = session.createQuery("from Book ", Book.class).list();

            for(Book eachBook : bookList)
                System.out.println("eachBook = " + eachBook);

            tx.commit();// carefull here about introducing side effects
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }

        sessionFactory.close();
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