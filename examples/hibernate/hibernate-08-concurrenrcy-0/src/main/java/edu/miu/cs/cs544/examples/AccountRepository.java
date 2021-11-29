package edu.miu.cs.cs544.examples;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import lombok.Data;

@Data
public class AccountRepository {

	private final SessionFactory sessionFactory;

	public Integer createAccount(Account account) {

		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.persist(account);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return account.getId();
	}
	
	public Account findAccountByAccountId(Integer accountId) {
		Session session = null;
		Transaction tx = null;
		
		Account account = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			account = findAccountByAccountId(accountId, session);
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return account;
	}
	
	private Account findAccountByAccountId(Integer accountId, Session session) {
		Query<Account> query = session.createQuery("from Account where id = :id", Account.class);
		query.setParameter("id", accountId);
		
		return query.uniqueResult();	
	}

	public void depositOneDollar(Integer accountId) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Account account = findAccountByAccountId(accountId, session);
			
			//Account account = session.get(Account.class, accountId, LockMode.PESSIMISTIC_WRITE);

			// Pessimistic Locking
//			session.lock(account, LockMode.PESSIMISTIC_WRITE);
//			session.refresh(account);

			System.out.println("Account before deposit: " + account);
			
			// Create a delay between 100-1000 milliseconds
			Long randomDelay = Math.round(900 * Math.random()) + 100;
			
			Thread.sleep(randomDelay);
			
			account.setBalance(account.getBalance() + 1);
			
			session.update(account);

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
