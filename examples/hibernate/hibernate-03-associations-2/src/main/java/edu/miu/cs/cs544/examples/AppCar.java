package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppCar {

    private static final SessionFactory sessionFactory;

    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class,Car.class));
	}

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            Customer john = new Customer("John", "Doe");
            session.persist(john);

            // Create new instance of Car and set values in it
            Car car1 = new Car("BMW", "2015", 30221.00, john);
            // save the car
            System.out.println("Step 1");
            session.persist(car1);
            // Create new instance of Car and set values in it
            Car car2 = new Car("Mercedes", "2016", 4088.00, john);
            // save the car
            System.out.println("Step 2");
            session.persist(car2);

            System.out.println("Step 3");
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

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all cars
            @SuppressWarnings("unchecked")
            List<Car> carList = session.createQuery("from Car").list();

            System.out.println("\nList of cars:\n");
            for (Car car : carList) {
                System.out.println(car);
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
