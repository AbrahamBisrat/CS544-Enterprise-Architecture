package edu.miu.cs.cs544.exercise09_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {

    private static List<Class> classList = Arrays.asList(Owner.class, Pet.class);

	public static void main(String[] args) {

        firstPart();            // persisting
        secondPart();           // retrieving
        System.exit(0);

    }

    private static void firstPart() {
        apply(session -> {
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
        });
    }

    private static void secondPart() {
        apply(session -> {
            // start time
            long start = System.nanoTime();
            Criteria criteria = session.createCriteria(Owner.class);

            List<Owner> ownerlist = criteria.list();

            for (Owner owner : ownerlist) {
                for (Pet pet : owner.getPets()) {
                    System.out.println("Owner name= " + owner.getName()
                            + "pet name= " + pet.getName());
                }
            }

            // stop time
            long stop = System.nanoTime();
            System.out.println("To fetch this data from the database took "
                    + (stop - start) / 1000000 + " milliseconds.");
        });
    }

    private static void apply(Consumer<Session> consumer) {
        try {
            final Session session = HibernateUtils.getSession(classList);
            Transaction tx = session.beginTransaction();

            consumer.accept(session);

            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
