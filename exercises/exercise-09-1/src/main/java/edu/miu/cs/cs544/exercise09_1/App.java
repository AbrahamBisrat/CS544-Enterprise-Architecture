package edu.miu.cs.cs544.exercise09_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    private static List<Class> classList = Arrays.asList(Owner.class, Pet.class);
    /**
     *
     * Benchmarks                               Time(ms)
     *
     * No Optimizations      > 8297(1) + 7168(2) + 7848(3)
     * Owner's Batch Optimizations
     * batchSize(10)
     * batchSize(5)
     * batchSize(50)
     *
     */

	public static void main(String[] args) {

//        saving(1000);
        retrieve();
        System.exit(0);

    }

    private static void saving(int owners) {
        apply(session -> {
            for (int x = 0; x < owners; x++) {
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

    private static void retrieve() {
        apply(session -> {
            // start time
            long start = System.nanoTime();
            Criteria criteria = session.createCriteria(Owner.class);
            List<Owner> owners = criteria.list();

            for (Owner owner : owners) {
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
