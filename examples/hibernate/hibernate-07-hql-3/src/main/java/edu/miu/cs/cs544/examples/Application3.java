package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application3 {

    private static final SessionFactory sessionFactory;

    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Actor.class,Film.class));
	}

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Query
            Query query = session.createQuery("Select Distinct a from Actor a left join fetch a.films f where f.rating like :rating");
            query.setParameter("rating", "PG%");
            query.setMaxResults(50);
            @SuppressWarnings("unchecked")
			List<Actor> actors = query.list();
            for (Actor actor : actors) {
                System.out.println(actor);
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
