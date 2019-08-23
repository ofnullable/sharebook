package org.slam.mapper.account;

import org.slam.dto.account.AccountDto;

import java.util.List;

public interface AccountSelectMapper {

    List<AccountDto> findAll();

    AccountDto findById(String username);

    void delete(AccountDto account);

}