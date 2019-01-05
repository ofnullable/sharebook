package org.slam.service.account;

import org.slam.dto.account.Account;
import org.slam.mapper.account.AccountUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountUpdateService {
	
	@Autowired
	private AccountUpdateMapper accountUpdateMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void updatePassword(Account account) {
		account.setPassword( passwordEncoder.encode(account.getPassword()) );
		accountUpdateMapper.updatePassword(account);
	}
	
}
