package edu.miu.cs.cs544.exercise12_1.bank.service;

import java.util.Collection;

import edu.miu.cs.cs544.exercise12_1.bank.dao.AccountDAO;
import edu.miu.cs.cs544.exercise12_1.bank.dao.IAccountDAO;
import edu.miu.cs.cs544.exercise12_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise12_1.bank.domain.Customer;
import edu.miu.cs.cs544.exercise12_1.bank.jms.IJMSSender;
import edu.miu.cs.cs544.exercise12_1.bank.jms.JMSSender;
import edu.miu.cs.cs544.exercise12_1.bank.logging.ILogger;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Setter
public class AccountService implements IAccountService {
	ConfigurableApplicationContext contextAccountDao =
			new ClassPathXmlApplicationContext("springdaoconfig.xml");
	ConfigurableApplicationContext contextJms =
			new ClassPathXmlApplicationContext("springjmsconfig.xml");


	private IAccountDAO accountDAO = contextAccountDao.getBean("accountDAO", AccountDAO.class);
	private IJMSSender jmsSender = contextJms.getBean("jmsSender", JMSSender.class);
	{
		System.out.println("contextAccountDao = " + accountDAO);
		System.out.println("contextJmsConfig = " + jmsSender);
	}
	private ICurrencyConverter currencyConverter;
	private ILogger logger;

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public Account getAccount(long accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
