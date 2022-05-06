package edu.miu.cs.cs544.exercise16_1.bank.service;

import java.util.Collection;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;



public interface AccountService {
    Account createAccount(long accountNumber, String customerName);
    Account getAccount(long accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (long accountNumber, double amount);
    void withdraw (long accountNumber, double amount);
    void depositEuros (long accountNumber, double amount);
    void withdrawEuros (long accountNumber, double amount);
    void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}
