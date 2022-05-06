package edu.miu.cs.cs544.exercise16_1.bank.dao;

import java.util.Collection;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;



public interface AccountDAO {
	void saveAccount(Account account);
	void updateAccount(Account account);
	Account loadAccount(long accountnumber);
	Collection<Account> getAccounts();
}
