package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application1 {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Country.class,City.class,Address.class,Customer.class));
	}

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Query
            Query<Customer> query = session.getNamedQuery("Customer.All");
            query.setMaxResults(50);
			List<Customer> customers = query.list();
            for (Customer customer : customers) {
                System.out.println(customer);
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
