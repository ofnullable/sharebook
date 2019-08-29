package org.slam.publicshare.mapper.account;

import org.slam.publicshare.dto.account.AccountDto;

import java.util.List;

public interface AccountSelectMapper {

    List<AccountDto> findAll();

    AccountDto findById(String username);

    void delete(AccountDto account);

}