package org.slam.publicshare.service.account;

import lombok.AllArgsConstructor;
import org.slam.publicshare.dto.account.AccountDto;
import org.slam.publicshare.mapper.account.AccountSelectMapper;

import java.util.List;

@AllArgsConstructor
public class AccountSelectService {

    private final AccountSelectMapper accountSelectMapper;

    public List<AccountDto> findAll() {
        return accountSelectMapper.findAll();
    }

    public void remove(AccountDto account) {
        accountSelectMapper.delete(account);
    }

}