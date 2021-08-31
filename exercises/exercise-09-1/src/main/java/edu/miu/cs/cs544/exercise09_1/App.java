package edu.miu.cs.cs544.exercise09_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {

	private static final SessionFactory sessionFactory;
	
	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Owner.class,Pet.class));
	}

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            for (int x = 0; x < 1000; x++) {
                Owner owner = new Owner("Frank" + x);
                List<Pet> petlist = new ArrayList<Pet>();
                for (int y = 0; y < 10; y++) {
                    Pet pet = new Pet("Garfield" + x + "-" + y);
                    petlist.add(pet);
                }
                owner.setPets(petlist);
                session.persist(owner);
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // start time
        long start = System.nanoTime();

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            @SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Owner.class);
            @SuppressWarnings("unchecked")
            List<Owner> ownerlist = criteria.list();
            for (Owner owner : ownerlist) {
                for (Pet pet : owner.getPets()) {
                    System.out.println("Owner name= " + owner.getName()
                            + "pet name= " + pet.getName());
                }
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        // stop time
        long stop = System.nanoTime();
        System.out.println("To fetch this data from the database took "
                + (stop - start) / 1000000 + " milliseconds.");
        System.exit(0);
    }

}
