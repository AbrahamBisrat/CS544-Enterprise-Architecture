package edu.miu.cs.cs544;

public class DataAccess {
	public Account loadAccount(long accountNumber) {
		// In reality this will go to DB and fetch a real Account object
		return new Account(accountNumber);
	}

	public void saveAccount(Account account) {
		// Do nothing. In reality, save to DB.
	}
}