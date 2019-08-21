package org.slam.account.service;

import lombok.RequiredArgsConstructor;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.domain.RoleName;
import org.slam.account.dto.SignUpRequest;
import org.slam.account.exception.UsernameDuplicationException;
import org.slam.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountSaveService {

    private final AccountFindService accountFindService;
    private final AccountRepository accountRepository;

    @Transactional
    public Account save(SignUpRequest dto) {
        if (accountFindService.existedEmail(dto.getEmail())) {
            throw new UsernameDuplicationException(dto.getEmail());
        }
        var entity = dto.toEntity();
        entity.addRole(RoleName.BASIC);
        return accountRepository.save(entity);
    }



}
