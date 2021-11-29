package edu.miu.cs.cs544.examples;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils2.getSessionFactory(Arrays.asList(
				City.class,
				Country.class
			));
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Retrieve all countries
            Query query = session.createQuery("from Country");
            //query.setMaxResults(10);
			query.list().forEach(System.out::println);
			
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
