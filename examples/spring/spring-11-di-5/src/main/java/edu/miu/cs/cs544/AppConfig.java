package edu.miu.cs.cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {
	@Bean
	@Lazy(true)
	public AccountService accountService() {
		AccountService accountService = new AccountService();
		accountService.setAccountDAO(accountDAO());

		return accountService;
	}

	@Bean
	public AccountDAO accountDAO() {
		return new AccountDAOImpl();
	}

	@Bean
	public DataAccess dataAccess() {
		return new DataAccess();
	}

}
