package org.slam.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.exception.AccountNotFoundException;
import org.slam.account.repository.AccountRepository;
import org.slam.config.security.userdetails.AccountDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountFindService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var email = Email.of(username);
        return accountRepository.findByEmail(email)
                .map(AccountDetails::new)
                .orElseThrow(() -> {
                    log.debug("There is no result for username: {}", username);
                    return new UsernameNotFoundException("No such account: " + username, new AccountNotFoundException(username));
                });
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean existedEmail(Email email) {
        return accountRepository.existsByEmail(email);
    }

}
