package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new UsernameNotFoundException("No such account: " + username, new ResourceNotFoundException(username, Account.class)));
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Account.class));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public boolean existedEmail(Email email) {
        return accountRepository.existsByEmail(email);
    }

}
