package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final PasswordEncoder passwordEncoder;
    private final AccountFindService accountFindService;

    @Transactional
    public Account updatePassword(Long id, String password) {
        var account = accountFindService.findById(id);
        account.updatePassword(passwordEncoder.encode(password));
        return account;
    }

}
