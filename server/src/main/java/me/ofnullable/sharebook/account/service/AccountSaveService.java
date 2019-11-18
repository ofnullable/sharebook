package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.RoleName;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountSaveService {

    private final PasswordEncoder passwordEncoder;
    private final AccountFindService accountFindService;
    private final AccountRepository accountRepository;

    @Transactional
    public Account saveAndSignIn(SignUpRequest dto) {
        if (accountFindService.existedEmail(dto.getEmail())) {
            throw new EmailDuplicationException(dto.getEmail());
        }
        var entity = dto.toEntity(passwordEncoder);
        entity.addRole(RoleName.BASIC);

        var account = accountRepository.save(entity);
        return signIn(account);
    }

    private Account signIn(Account account) {
        var accountDetails = new AccountDetails(account);
        var authToken = new UsernamePasswordAuthenticationToken(accountDetails, accountDetails.getPassword(), accountDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        return account;
    }

}
