package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.RoleName;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.repository.AccountRepository;
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
