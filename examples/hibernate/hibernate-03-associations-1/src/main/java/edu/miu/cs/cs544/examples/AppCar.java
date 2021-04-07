package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppCar {

    private static final SessionFactory sessionFactory;
    //private static final ServiceRegistry serviceRegistry;
    
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
            Car car1 = new Car("BMW", "2015", 30221.00);
            // save the car
            System.out.println("Step 1");
            session.persist(car1);
            // Create new instance of Car and set values in it
            Car car2 = new Car("Mercedes", "2016", 40880.00);
            // save the car
            System.out.println("Step 2");
            session.persist(car2);
            
            john.addCar(car1);
            john.addCar(car2); 

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
