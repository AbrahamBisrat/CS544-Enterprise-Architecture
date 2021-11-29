package edu.miu.cs.cs544.examples;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Application {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Account.class));
	}

	public static void main(String[] args) {
		
        Session session1 = null;
        Session session2 = null;
        
        Transaction tx1 = null;
        Transaction tx2 = null;

        try {
            session1 = sessionFactory.openSession();
            session2 = sessionFactory.openSession();

            tx1 = session1.beginTransaction();
            
            Account account = new Account();
            Integer accountId = (Integer)session1.save(account);

            tx1.commit();
            
            tx1 = session1.beginTransaction();
    		Account account1 = getAccount(session1, accountId);
            
            tx2 = session2.beginTransaction();
    		Account account2 = getAccount(session2, accountId);
    		
    		account1.setBalance(1000.0);
            tx1.commit();

            account2.setBalance(2000.0);
            tx2.commit();

        } catch (Exception e) {
        	e.printStackTrace();
        	rollback(tx1);
        	rollback(tx2);
        } finally {
        	closeSession(session1);
        	closeSession(session2);
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
	
	private static Account getAccount(Session session, Integer id) {
		Query<Account> query = session.createQuery("from Account where id = :id", Account.class);
		query.setParameter("id", id);

		return query.uniqueResult();
	}
	
	private static void rollback(Transaction tx) {
        if (tx != null) {
            tx.rollback();
        }
	}
	
	private static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }		
	}
}
