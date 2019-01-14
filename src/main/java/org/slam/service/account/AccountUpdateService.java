package org.slam.service.account;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.mapper.account.AccountUpdateMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AccountUpdateService {

    private final AccountUpdateMapper accountUpdateMapper;
    private final PasswordEncoder passwordEncoder;

    public void updatePassword(Account account) {
        account.setPassword( passwordEncoder.encode(account.getPassword()) );
        accountUpdateMapper.updatePassword(account);
    }

}