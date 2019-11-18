package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.exception.PasswordNotMatchingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountVerifyService {

    private final PasswordEncoder passwordEncoder;

    public Account verify(Account account, String password) {
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new PasswordNotMatchingException();
        }

        return account.verified();
    }
}
