package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	
	private static final SessionFactory sessionFactory;
	
	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Person.class));
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of Employee and set values in it
			Person person1 = new Person("John", "Doe");
			Person person2 = new Person("Frank", "Miller");
			
			// save the person
			session.persist(person1);
			session.persist(person2);
			System.out.println(person1);
			session.persist(person1);
			System.out.println(person1);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all persons
			List<Person> personList = session.createQuery("from Person").list();
			for (Person p : personList) {
				System.out.println(p);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}