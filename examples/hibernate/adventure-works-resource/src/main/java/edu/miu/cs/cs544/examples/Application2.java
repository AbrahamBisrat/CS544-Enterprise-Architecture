package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application2 {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils2.getSessionFactory(Arrays.asList(
				CountryRegion.class,
				StateProvince.class,
				BusinessAddress.class,
				BusinessAddressType.class,
				BusinessEntity.class,
				ContactType.class,
				Person.class
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

            // retieve all cars
            Query query = session.createQuery("from CountryRegion where id = 'US'");
			CountryRegion country = (CountryRegion) query.uniqueResult();
            for (StateProvince state : country.getStates()) {
                System.out.println(state);
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
