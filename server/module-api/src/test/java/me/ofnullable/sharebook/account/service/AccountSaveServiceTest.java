package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.domain.RoleName;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildNormalSignUpRequest;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountSaveServiceTest {

    @InjectMocks
    private AccountSaveService accountSaveService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final SignUpRequest signUpRequest = buildNormalSignUpRequest("test@test.com");

    @Test
    @DisplayName("회원가입")
    void save_account() {
        given(passwordEncoder.encode(any(String.class)))
                .willReturn("test");
        var entity = signUpRequest.toEntity(passwordEncoder);
        entity.addRole(RoleName.BASIC);

        given(accountRepository.save(any(Account.class)))
                .willReturn(entity);

        var account = accountSaveService.saveAndSignIn(signUpRequest);

        then(account.getEmail().getAddress())
                .isEqualTo(entity.getEmail().getAddress());
        then(account.getPassword())
                .isEqualTo(entity.getPassword());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    void save_account_with_duplicated_email() {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(true);

        assertThrows(EmailDuplicationException.class, () -> accountSaveService.saveAndSignIn(signUpRequest));
    }

}
