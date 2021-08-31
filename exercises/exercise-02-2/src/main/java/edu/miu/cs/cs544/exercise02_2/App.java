package edu.miu.cs.cs544.exercise02_2;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {

	private static final SessionFactory sessionFactory;

    static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Laptop.class));
	}

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            Laptop laptop1 = new Laptop("Apple", "iBook", df.parse("04/17/2006"));
            Laptop laptop2 = new Laptop("IBM", "t60", df.parse("03/30/07"));

            session.persist(laptop1);
            session.persist(laptop2);

            tx.commit();
        } catch (HibernateException | ParseException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Laptop> laptops = session.createQuery("from Laptop").list();
            for (Laptop laptop : laptops) {
                System.out.println(laptop.getBrand() + " " + laptop.getModel()
                        + " " + laptop.getPurchase_date());
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
    }
}
