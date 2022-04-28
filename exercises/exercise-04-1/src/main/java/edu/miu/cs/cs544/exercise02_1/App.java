package edu.miu.cs.cs544.exercise02_1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        tests();
    }

    public static void tests() {
        apply(session -> {
            Employee emp1 = Employee.create("John", "Smith");
            Employee emp2 = Employee.create("Jane", "Doe");
            Employee emp3 = Employee.create("Anna", "Lynn");

            Laptop laptop1 = Laptop.create("Apple", "MacBook Pro");
            Laptop laptop2 = Laptop.create("Dell", "Latitude D 600");

            Passenger passenger1 = Passenger.create("passenger 1");
            Passenger passenger2 = Passenger.create("passenger 2");
            Passenger passenger3 = Passenger.create("passenger 3");

            Flight flight1 = Flight.create("ET500", "Addis Ababa", "Washington Dulles", LocalDate.now());
            Flight flight2 = Flight.create("ET523", "Addis Ababa", "JFK", LocalDate.now());
            Flight flight3 = Flight.create("ET534", "Addis Ababa", "London", LocalDate.now());

            School school = School.create("Avengers");

            Student stud1 = Student.create("John", "Mattew");
            Student stud2 = Student.create("Denver", "House");
            Student stud3 = Student.create("Anthony", "Stark");

//            emp1.setLaptops(Set.of(laptop1));

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
