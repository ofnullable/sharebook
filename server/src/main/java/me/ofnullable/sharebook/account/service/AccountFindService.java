package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.exception.NoSuchAccountException;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
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
                    return new UsernameNotFoundException("No such account: " + username, new NoSuchAccountException(username));
                });
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchAccountException(id));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean existedEmail(Email email) {
        return accountRepository.existsByEmail(email);
    }

}
