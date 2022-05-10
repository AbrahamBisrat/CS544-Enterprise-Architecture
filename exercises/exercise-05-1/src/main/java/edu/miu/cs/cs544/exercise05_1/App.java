package edu.miu.cs.cs544.exercise05_1;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private static List<Class> classList
            = Arrays.asList(Book.class, CD.class, Customer.class, DVD.class,
                                Order.class, OrderLine.class, Product.class);

    public static void main(String[] args) {
        // tests();
        // fakerApiServicesTest();
        fakerClassTest();
        
    }

    private static void fakerClassTest() {
        Faker faker = new Faker();

        addressGeneration(faker);
        rickAndMorty(faker);
    }

    private static void rickAndMorty(Faker faker) {
        String character = faker.rickAndMorty().character();
        String roles = faker.rickAndMorty().character().intern();
        String coolQuote = faker.rickAndMorty().quote();
        String location = faker.rickAndMorty().location();

        System.out.println("\nRick and morty generation test "
                            + "\n" + character
                            + "\n" + roles
                            + "\n" + coolQuote
                            + "\n" + location );
    }

    private static void addressGeneration(Faker faker) {
        String streetName = faker.address().streetName();
        String city = faker.address().city();
        String country = faker.country().name();

        System.out.println("Address generation "
                + "\n" + streetName
                + "\n" + city
                + "\n" + country);
    }

    private static void fakerApiServicesTest() {
        FakeValuesService faker = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String alphaNumericString = faker.regexify("[a-z]-9]{10}");
        Matcher alphaNumericMatcher = Pattern.compile("[a-z]-9]{10}").matcher(alphaNumericString);

        System.out.println(alphaNumericString);
        System.out.println(alphaNumericMatcher);

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
