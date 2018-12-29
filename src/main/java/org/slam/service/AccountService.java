package org.slam.service;

import lombok.extern.log4j.Log4j2;
import org.slam.entity.account.Account;
import org.slam.entity.account.AccountDetails;
import org.slam.entity.account.Role;
import org.slam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Log4j2
@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findById(username)
				.map(AccountDetails::new)
				.orElseThrow( () -> new UsernameNotFoundException("Can not find user. username : " + username) );
	}
	
	public Account save(Account account) {
		account.setPassword( passwordEncoder.encode(account.getPassword()) );
		return accountRepo.save(account);
	}
	
	public void remove(Account account) {
		accountRepo.delete(account);
	}
	
	@PostConstruct
	private void init() {
		accountRepo.findById("default")
				.ifPresentOrElse(
						a -> log.info("DEFAULT ACCOUNT : {}", a),
						() -> {
							var defaultAccount = new Account("default", "pass", "ADMIN", Collections.singleton(new Role("ADMIN")));
							log.info("SAVE DEFAULT ACCOUNT : {}", this.save(defaultAccount));
						}
				);
	}
	
}
