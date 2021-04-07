package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppQueryAll {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Country.class,City.class,Address.class));
	}

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // 1. retrieve the list of first 50 countries using a Hibernate query
            Query query1 = session.createQuery("from java.lang.Object");
            List list1 = query1.list();
            System.out.println("\nList of first 50 countries:\n");
            for (Object o : list1) {
                System.out.println(o);
            }
            
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
