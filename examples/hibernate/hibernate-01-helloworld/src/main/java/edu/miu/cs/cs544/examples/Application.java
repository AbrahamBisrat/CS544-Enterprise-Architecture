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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Employee.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// Create new instance of Employee and set values in it
			Employee employee = new Employee();
			employee.setFirstname("Frank");
			employee.setLastname("Miller");
			// save the employee
			session.persist(employee);
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
			// retrieve all employees
			List<Employee> employeeList = session.createQuery("from Employee").list();
			for (Employee emp : employeeList) {
				System.out.println("firstname= " + emp.getFirstname() + ", lastname= " + emp.getLastname());
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