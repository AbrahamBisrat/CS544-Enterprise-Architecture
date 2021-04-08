package edu.miu.cs.cs544;

public class AccountService {
	private AccountDAO accountDAO;

	public AccountService(AccountDAO accountDAO) {
		super();
		this.accountDAO = accountDAO;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.saveAccount(account);
	}
}
