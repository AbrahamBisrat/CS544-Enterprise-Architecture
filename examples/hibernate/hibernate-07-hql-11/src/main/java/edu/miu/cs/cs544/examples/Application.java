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
		sessionFactory = HibernateUtils2.getSessionFactory(Arrays.asList(CountryRegion.class));
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
            Query<CountryRegion> query1 = session.createQuery("from CountryRegion c where c.id like 'A%'", CountryRegion.class);
            List<CountryRegion> list1 = query1.list();
            System.out.println("\nList of all the countries whose two letter code starts with an A:\n");
            for (CountryRegion country : list1) {
                System.out.println(country);
            }
            
            // 2. retrieve the list of ALL countries using a Hibernate named query
            Query<CountryRegion> query2 = session.getNamedQuery("Country.All"); 
            List<CountryRegion> list2 = query2.list();
            System.out.println("\nList of ALL countries:\n");
            for (CountryRegion country : list2) {
                System.out.println(country);
            }

            // 3. query result size
            Query<CountryRegion> query3 = session.getNamedQuery("Country.All");            
            query3.setFirstResult(0);
            query3.setMaxResults(50);
            List<CountryRegion> list3 = query3.list();
            System.out.println("\nList of first 50 countries:\n");
            for (CountryRegion country : list3) {
                System.out.println(country);
            }
            
            query3.setFirstResult(50);
            query3.setMaxResults(100);
            List<CountryRegion> list4 = query3.list();
            System.out.println("\nList of second 50 countries:\n");
            for (CountryRegion country : list4) {
                System.out.println(country);
            }

            // 4. query result size
            Query<CountryRegion> query4 = session.createQuery("from CountryRegion c where c.id = :id", CountryRegion.class);
            query4.setParameter("id", "US");
            CountryRegion country = (CountryRegion) query4.uniqueResult();
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
