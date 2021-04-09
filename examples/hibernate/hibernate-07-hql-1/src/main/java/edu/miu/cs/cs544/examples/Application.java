package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application {

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
            Query<Country> query1 = session.createQuery("from Country c where c.id < 50", Country.class);
            List<Country> list1 = query1.list();
            System.out.println("\nList of first 50 countries:\n");
            for (Country country : list1) {
                System.out.println(country);
            }
            
            // 2. retrieve the list of ALL countries using a Hibernate named query
            Query<Country> query2 = session.getNamedQuery("Country.All"); 
            List<Country> list2 = query2.list();
            System.out.println("\nList of ALL countries:\n");
            for (Country country : list2) {
                System.out.println(country);
            }

            // 3. query result size
            Query<Country> query3 = session.getNamedQuery("Country.All");            
            query3.setFirstResult(0);
            query3.setMaxResults(50);
            List<Country> list3 = query3.list();
            System.out.println("\nList of first 50 countries:\n");
            for (Country country : list3) {
                System.out.println(country);
            }
            
            query3.setFirstResult(50);
            query3.setMaxResults(100);
            List<Country> list4 = query3.list();
            System.out.println("\nList of second 50 countries:\n");
            for (Country country : list4) {
                System.out.println(country);
            }

            // 4. query result size
            Query<Country> query4 = session.createQuery("from Country c where c.id = :id", Country.class);
            query4.setParameter("id", 50L);
            Country country = (Country) query4.uniqueResult();
            System.out.print("\nCountry at index ") ; 
            System.out.println(country);

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
