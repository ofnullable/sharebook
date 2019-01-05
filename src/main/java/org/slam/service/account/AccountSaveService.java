package org.slam.service.account;

import org.slam.dto.account.Account;
import org.slam.mapper.account.AccountRoleMapper;
import org.slam.mapper.account.AccountSaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountSaveService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountSaveMapper accountSaveMapper;
	@Autowired
	private AccountRoleMapper accountRoleMapper;
	
	@Transactional
	public void save(Account account) {
		account.setPassword( passwordEncoder.encode(account.getPassword()) );
		accountSaveMapper.save(account);
		accountRoleMapper.save(account.getUsername(), 1L);
		// TODO: Make enum for default role value
	}
	
}
