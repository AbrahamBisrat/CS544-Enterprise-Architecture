package edu.miu.cs.cs544.exercise05_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class App {

    private static List<Class> classList = Arrays.asList(Book.class, CD.class, Customer.class, DVD.class,
            Order.class, OrderLine.class, Product.class);

    public static void main(String[] args) {
        tests();
    }

    public static void tests() {
        apply(session -> {
            // truncate tables => a lot faster than using ddl-auto: create or create-drop
//            classList.forEach(eachTable -> session.createSQLQuery("truncate table " + eachTable.getName()));//.executeUpdate());
//            session.flush();

            Product p1 = CD.create("artist", "artist name", "very creative naming");
            Product p2 = DVD.create("Samsung Galaxy S4", "some some some", "some some");
            Product p3 = Book.create("Samsung Galaxy S8+", "some some some", "some some");

            OrderLine ol1 = OrderLine.create(10, p1);
            OrderLine ol2 = OrderLine.create(10, p2);
            OrderLine ol3 = OrderLine.create(10, p3);

            Order order1 = Order.create(LocalDate.now());
            Order order2 = Order.create(LocalDate.now().plusDays(1));
            Order order3 = Order.create(LocalDate.now().minusWeeks(1));

            order1.setOderLines(Arrays.asList(ol1, ol2, ol3));

            Customer cust1 = Customer.create("John", "Smith");
            Customer cust2 = Customer.create("Jane", "Doe");
            Customer cust3 = Customer.create("Anna", "Lynn");

            cust1.setOrders(Arrays.asList(order1, order2, order3));

            session.persist(cust1);
            session.persist(cust2);
            session.persist(cust3);
        });
    }

    private static void apply(Consumer<Session> consumer) {
        try {
            final Session session = HibernateUtils.getSession(classList);
            Transaction txn = session.beginTransaction();
            consumer.accept(session);
            txn.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
