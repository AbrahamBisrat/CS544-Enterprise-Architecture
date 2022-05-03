package edu.miu.cs.cs544.exercise09_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.*;

/**
 * Disclaimer: MySql server is running on Home-server one hop away
 *
 * @Benchmarks                               @Time(ms)
 *
 * No Optimizations      > 8297(1) + 7168(2) + 7848(3) + 7142(4) + 6935(5)
 *
 * Owner's @BatchSize Optimizations
 * @batchSize(10)         > 2378(1) + 2374(2) + 2492(3) + 1792(4) + 2180(5)
 * @batchSize(5)          > 3835(1) + 4083(2) + 4951(3) + 2460(4) + 2581(5)
 * @batchSize(50)         > 1269(1) + 1483(2) + 1119(3) + 1235(4) + 1148(5)
 * @batchSize(100)        > 1155(1) + 1037(2) + 1144(3) + 1904(4) + 1135(5)
 * @batchSize(1000)       > 1399(1) + 1572(2) + 760(3) + 649(4) + 742(5)
 *
 * @Fetch(value = FetchMode.SUBSELECT)
 *                        > 855(1) + 748(2) + 700(3) + 724(4) + 685(5)
 * @FetchJoin on * Criteria in Application.java
 *                        > 1229(1) + 1638(2) + 2074(3) + 1090(4) + 1157(5)
 * @Fetch(FetchMode.JOIN) => Bring all the children along with the queried object
 *                        > 1440(1) + 1217(2) + 1352(3) + 1321(4) + 1287(5)
 *
 * @FetchType.EAGER       > 1493(1) + 1203(2) + 1175(3) + 1219(4) + 1193(5)
 * @FetchType.Lazy        > 10807(1) + 8667(2) + 6641(3) + 6991(4) + 7190(5)
 *
 */

public class App {

    private static List<Class> classList = Arrays.asList(Owner.class, Pet.class);

	public static void main(String[] args) {

//        saving(1000);
        retrieve();
        System.exit(0);

    }

    private static void saving(int owners) {
        apply(session -> {
            for (int x = 0; x < owners; x++) {
                Owner owner = Owner.create("Frank" + x);
                List<Pet> petlist = new ArrayList<Pet>();
                for (int y = 0; y < 10; y++) {
                    Pet pet = Pet.create("Garfield" + x + "-" + y);
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
            Criteria criteria = session.createCriteria(Owner.class);//.setFetchMode("pets", FetchMode.JOIN);
            List<Owner> owners = criteria.list();

            for (Owner owner : owners) {
                for (Pet pet : owner.getPets()) {
                    System.out.println("Owner name= " + owner.getName()
                            + "\tpet name= " + pet.getName());
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
