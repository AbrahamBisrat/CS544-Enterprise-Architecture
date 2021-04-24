package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Ball.class,Person.class,Pet.class,StuffedAnimal.class,Toy.class));
	}

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            Person john = new Person("John", "Doe");
            
            Pet cat = new Pet("Meow", new Date());
            Pet dog = new Pet("Max", new Date());
            
            john.addPet(cat);
            john.addPet(dog);
            
            Toy toy1 = new Ball("Ball", 10);
            Toy toy2 = new StuffedAnimal("Beanie Boo", "Cat");
            Toy toy3 = new Ball("Ball", 15);
            Toy toy4 = new StuffedAnimal("Beanie Boo", "Dog");
            
            cat.addToy(toy1);
            cat.addToy(toy2);

            dog.addToy(toy3);
            dog.addToy(toy4);
            
            session.persist(john);
            
            session.persist(cat);
            session.persist(dog);

            session.persist(toy1);
            session.persist(toy2);
            session.persist(toy3);
            session.persist(toy4);

            // Query
            Query<Person> query = session.createQuery("from Person", Person.class);
			List<Person> persons = query.list();
            for (Person person : persons) {
                System.out.println(person);
            }
            
            Query<Person> query1 = session.createQuery("Select p from Person p join p.pets pet where pet.name='Meow'", Person.class);
			List<Person> result1 = query1.list();
            for (Person person : result1) {
                System.out.println(person);
            }
            
            Query<Pet> query2 = session.createQuery("Select p from Pet p join p.toys t where p.person.name='John' and t.description='Ball'", Pet.class);
			List<Pet> result2 = query2.list();
            for (Pet pet : result2) {
                System.out.println(pet);
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
