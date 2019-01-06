package org.slam.service.account;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.mapper.account.AccountRoleMapper;
import org.slam.mapper.account.AccountSaveMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountSaveService {
	
	private final PasswordEncoder passwordEncoder;
	private final AccountSaveMapper accountSaveMapper;
	private final AccountRoleMapper accountRoleMapper;
	
	@Transactional
	public void save(Account account) {
		account.setPassword( passwordEncoder.encode(account.getPassword()) );
		accountSaveMapper.save(account);
		accountRoleMapper.save(account.getUsername(), 1L);
		// TODO: Make enum for default role value
	}
	
}
