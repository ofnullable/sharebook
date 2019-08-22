package org.slam.service.account;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.mapper.account.AccountSelectMapper;

import java.util.List;

@AllArgsConstructor
public class AccountSelectService {

    private final AccountSelectMapper accountSelectMapper;

    public List<Account> findAll() {
        return accountSelectMapper.findAll();
    }

    public void remove(Account account) {
        accountSelectMapper.delete(account);
    }

}