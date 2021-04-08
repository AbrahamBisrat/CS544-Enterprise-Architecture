package edu.miu.cs.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	DataAccess dataAccess;

	public Account loadAccount(long accountNumber) {
		return dataAccess.loadAccount(accountNumber);
	}

	public void saveAccount(Account account) {
		dataAccess.saveAccount(account);
	}

}
