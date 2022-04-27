package edu.miu.cs.cs544.exercise02_1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import edu.miu.cs.cs544.exercise02_1.Assig.Owner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AppCar {

	public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partOne() {
        apply(session -> {
            Car car1 = Car.create("BMW", "SDA231", 30221.00);
            Car car2 = Car.create("Mercedes", "HOO100", 4088.00);

            Owner owner1 = Owner.create("First Owner", "1000 N 4th St");
            Owner owner2 = Owner.create("Second Owner", "10 N 4th St");

            car1.setOwner(owner1);
            car2.setOwner(owner2);

            session.persist(car1);
            session.persist(car2);
        });
    }

    private static void partTwo() {
        apply(session -> {
            Query<Car> carList = session.createQuery("from Car", Car.class);
            carList.getResultList().forEach(System.out::println);
        });
    }

    private static void apply(Consumer<Session> consumer) {
        try {
            final Session session = HibernateUtils.getSession(Arrays.asList(Car.class, Owner.class));

            Transaction txn = session.beginTransaction();

            consumer.accept(session);

            txn.commit();

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
