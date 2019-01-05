package org.slam.mapper.account;

import org.slam.dto.account.Account;

import java.util.List;

public interface AccountSelectMapper {
	
	List<Account> findAll();
	
	Account findById(String username);
	
	void delete(Account account);
	
}
