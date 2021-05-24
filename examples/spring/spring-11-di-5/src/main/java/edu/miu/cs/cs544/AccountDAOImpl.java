package edu.miu.cs.cs544;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountDAOImpl implements AccountDAO {

	@Autowired
	DataAccess dataAccess;

	public Account loadAccount(long accountNumber) {
		System.out.println("Real implementation of loadAccount is called");
		return dataAccess.loadAccount(accountNumber);
	}

	public void saveAccount(Account account) {
		System.out.println("Real implementation of saveAccount is called");
		dataAccess.saveAccount(account);
	}

}
