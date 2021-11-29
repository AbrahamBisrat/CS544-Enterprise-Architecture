package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.hibernate.SessionFactory;

public class Application {

    private static final SessionFactory sessionFactory;
    
    static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Account.class));
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		AccountRepository repository = new AccountRepository(sessionFactory);

		Integer accountId = repository.createAccount(new Account());
		
		repository.depositOneDollar(accountId);
		
		repository.depositOneDollar(accountId);
		
		CompletableFuture<Void> future1 = 
				CompletableFuture.runAsync(() -> repository.depositOneDollar(accountId));
		
		CompletableFuture<Void> future2 = 
				CompletableFuture.runAsync(() -> repository.depositOneDollar(accountId));

		// Waits till all futures are done and then returns
		CompletableFuture.allOf(future1, future2).join();
		
		Account account = repository.findAccountByAccountId(accountId);
		
		System.out.println("Account after deposit: " + account);
		
        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }

}
