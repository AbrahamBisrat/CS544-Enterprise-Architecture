package edu.miu.cs.cs544;

public interface AccountDAO {
	Account loadAccount(long accountNumber);

	void saveAccount(Account account);
}
