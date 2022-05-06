package edu.miu.cs.cs544.exercise16_1.bank.service;

import java.util.Collection;

import edu.miu.cs.cs544.exercise16_1.bank.dao.AccDAOImpl;
import edu.miu.cs.cs544.exercise16_1.bank.dao.AccountDAO;
import edu.miu.cs.cs544.exercise16_1.bank.dao.AccountDAOImpl;
import edu.miu.cs.cs544.exercise16_1.bank.dao.HibernateUtils;
import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise16_1.bank.domain.Customer;
import edu.miu.cs.cs544.exercise16_1.bank.jms.JMSSender;
import edu.miu.cs.cs544.exercise16_1.bank.jms.JMSSenderImpl;
import edu.miu.cs.cs544.exercise16_1.bank.logging.Logger;
import edu.miu.cs.cs544.exercise16_1.bank.logging.LoggerImpl;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	private CurrencyConverter currencyConverter;
	private JMSSender jmsSender;
	private Logger logger;

	private SessionFactory sf;
	
	public AccountServiceImpl(){
//		accountDAO=new AccountDAOImpl();
		accountDAO=new AccDAOImpl();
		currencyConverter= new CurrencyConverterImpl();
		jmsSender =  new JMSSenderImpl();
		logger = new LoggerImpl();
		sf = HibernateUtils.getSessionFactory();
	}

	public Account createAccount(long accountNumber, String customerName) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = Account.create(accountNumber);
		Customer customer = Customer.create(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		tx.commit();
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		tx.commit();
	}

	public Account getAccount(long accountNumber) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		tx.commit();
		return account;
	}

	public Collection<Account> getAllAccounts() {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Collection<Account> accountList = accountDAO.getAccounts();
		tx.commit();
		return accountList;
	}

	public void withdraw(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		tx.commit();
	}

	public void depositEuros(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		tx.commit();
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		tx.commit();
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
		tx.commit();
	}
}
