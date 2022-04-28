package edu.miu.cs.cs544.exercise02_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        tests();
    }

    public static void tests() {
        apply(session -> {

        });
    }

    private static void apply(Consumer<Session> consumer) {
        try {
            final Session session = HibernateUtils.getSession(
                    Arrays.asList(Employee.class, Flight.class, Laptop.class, Passenger.class,
                            School.class, Student.class));

            Transaction txn = session.beginTransaction();

            consumer.accept(session);

            txn.commit();

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
