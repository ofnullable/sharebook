package org.slam.service.account;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.dto.account.Account;
import org.slam.dto.account.AccountDetails;
import org.slam.mapper.account.AccountSelectMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class AccountSelectService implements UserDetailsService {
	
	private final AccountSelectMapper accountSelectMapper;
	
	@Override
	public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("PROCESSING LOGIN FOR USER : {}", username);
		return Optional.ofNullable(accountSelectMapper.findById(username))
				.map(AccountDetails::new)
				.orElseThrow( () -> new UsernameNotFoundException("Can not find user. username : " + username) );
	}
	
	public List<Account> findAll() {
		return accountSelectMapper.findAll();
	}
	
	public void remove(Account account) {
		accountSelectMapper.delete(account);
	}
	
}
