package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildNormalSignUpRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class AccountSaveServiceTest {

    @InjectMocks
    private AccountSaveService accountSaveService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;

    SignUpRequest signUpRequest = buildNormalSignUpRequest("test@test.com");

    @Test
    @DisplayName("회원가입")
    public void save_account() {
        given(accountRepository.save(any(Account.class)))
                .willReturn(signUpRequest.toEntity());

        var account = accountSaveService.save(signUpRequest);

        assertEquals(account.getEmail().getAddress(), account.getEmail().getAddress());
        assertEquals(account.getPassword(), account.getPassword());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    public void save_account_with_duplicated_email() {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(true);

        assertThrows(EmailDuplicationException.class, () -> accountSaveService.save(signUpRequest));
    }

}
