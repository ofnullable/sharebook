package org.slam.mapper.account;

import org.slam.dto.account.Account;

import java.util.List;

public interface AccountMapper {
	
	List<Account> findAll();
	
	Account findById(String username);
	
	void save(Account account);
	
	void delete(Account account);
	
}
