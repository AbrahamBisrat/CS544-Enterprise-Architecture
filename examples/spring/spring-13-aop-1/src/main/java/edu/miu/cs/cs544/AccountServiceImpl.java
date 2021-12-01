package edu.miu.cs.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	public void deposit(long accountNumber, double amount) {
		System.out.println("in execution of method deposit");
		Account account = accountDAO.loadAccount(accountNumber);
		//
		account.deposit(amount);
		accountDAO.saveAccount(account);
	}
}
