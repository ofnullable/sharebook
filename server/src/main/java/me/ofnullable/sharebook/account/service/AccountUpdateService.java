package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final PasswordEncoder passwordEncoder;
    private final AccountFindService accountFindService;

    @Transactional
    public Account update(UpdateAccountRequest dto) {
        var account = accountFindService.findById(dto.getId());
        return account.update(dto, passwordEncoder);
    }

}
