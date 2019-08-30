package org.slam.publicshare.service.account;

import lombok.AllArgsConstructor;
import org.slam.publicshare.dto.account.AccountDto;
import org.slam.publicshare.mapper.account.AccountUpdateMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class AccountUpdateService {

    private final AccountUpdateMapper accountUpdateMapper;
    private final PasswordEncoder passwordEncoder;

    public void updatePassword(AccountDto account) {
        account.setPassword( passwordEncoder.encode(account.getPassword()) );
        accountUpdateMapper.updatePassword(account);
    }

}