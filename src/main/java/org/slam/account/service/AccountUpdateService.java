package org.slam.account.service;

import lombok.RequiredArgsConstructor;
import org.slam.account.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountFindService accountFindService;

    @Transactional
    public Account updatePassword(Long id, String password) {
        var account = accountFindService.findById(id);
        account.updatePassword(password);
        return account;
    }

}
