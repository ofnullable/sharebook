package org.slam.service.account;

import lombok.AllArgsConstructor;
import org.slam.dto.account.AccountDto;
import org.slam.mapper.account.AccountSelectMapper;

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