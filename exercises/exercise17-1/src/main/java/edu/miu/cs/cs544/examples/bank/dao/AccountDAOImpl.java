package edu.miu.cs.cs544.examples.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs.cs544.examples.bank.domain.Account;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveAccount(Account account) {
		System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountNumber());
		sessionFactory.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
		System.out.println("AccountDAO: update account with accountnr ="+account.getAccountNumber());
		sessionFactory.getCurrentSession().update(account);
	}

	public Account loadAccount(long accountnumber) {
		System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		return sessionFactory.getCurrentSession().load(Account.class, accountnumber);
	}

	public Collection<Account> getAccounts() {
		return sessionFactory.getCurrentSession().createQuery("from Account").list();
	}

}
