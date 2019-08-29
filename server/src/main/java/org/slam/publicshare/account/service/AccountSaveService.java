package org.slam.publicshare.account.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.domain.RoleName;
import org.slam.publicshare.account.dto.SignUpRequest;
import org.slam.publicshare.account.exception.EmailDuplicationException;
import org.slam.publicshare.account.repository.AccountRepository;
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
            throw new EmailDuplicationException(dto.getEmail());
        }
        var entity = dto.toEntity();
        entity.addRole(RoleName.BASIC);
        return accountRepository.save(entity);
    }

}
