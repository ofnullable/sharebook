package org.slam.service;

import lombok.extern.log4j.Log4j2;
import org.slam.dto.account.Account;
import org.slam.dto.account.AccountDetails;
import org.slam.mapper.account.AccountMapper;
import org.slam.mapper.account.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("PROCESSING LOGIN FOR USER : {}", username);
		return Optional.ofNullable(accountMapper.findById(username))
				.map(AccountDetails::new)
				.orElseThrow( () -> new UsernameNotFoundException("Can not find user. username : " + username) );
	}
	
	public void save(Account account) {
		account.setPassword( passwordEncoder.encode(account.getPassword()) );
		account.setRoles( Set.of(roleMapper.findById(1L)) );
		accountMapper.save(account);
	}
	
	public void remove(Account account) {
		accountMapper.delete(account);
	}
	
	public List<Account> findAll() {
		return accountMapper.findAll();
	}
}
