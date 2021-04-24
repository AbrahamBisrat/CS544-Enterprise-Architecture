package edu.miu.cs.cs544.examples;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application1 {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils2.getSessionFactory(Arrays.asList(
				CountryRegion.class,
				StateProvince.class,
				BusinessAddress.class,
				BusinessAddressType.class,
				BusinessEntity.class,
				ContactType.class,
				Person.class
			));
	}

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Query
            Query<BusinessEntity> query = session.createQuery("select be from BusinessEntity be where be.id = 292");
			BusinessEntity businessEntity = query.uniqueResult();
            System.out.println(businessEntity);
            
//            // Query
//            Query<BusinessEntity> query2 = session.createQuery("Select be from BusinessEntity be where be.id > 292 AND be.id < 400");
//			List<BusinessEntity> entityList = query2.list();
//			entityList.forEach(System.out::println);
            
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
