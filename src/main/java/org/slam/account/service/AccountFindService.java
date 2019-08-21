package org.slam.account.service;

import lombok.RequiredArgsConstructor;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.domain.RoleName;
import org.slam.account.repository.AccountRepository;
import org.slam.config.security.userdetails.AccountDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(Email.of(username))
                .map(AccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Account save(Account account) {
        account.addRole(RoleName.BASIC);
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void remove(Account account) {
        accountRepository.delete(account);
    }

}
