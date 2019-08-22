package org.slam.account.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.dto.SignUpRequest;
import org.slam.account.exception.EmailDuplicationException;
import org.slam.account.repository.AccountRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.testutil.AccountUtils.buildNormalSignUpRequest;

@ExtendWith(SpringExtension.class)
public class AccountSaveServiceTest {

    @InjectMocks
    private AccountSaveService accountSaveService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;

    SignUpRequest signUpRequest = buildNormalSignUpRequest();

    @Test
    @DisplayName("회원가입")
    public void account_save() {
        given(accountRepository.save(any(Account.class)))
                .willReturn(signUpRequest.toEntity());

        var account = accountSaveService.save(signUpRequest);

        assertEquals(account.getEmail().getAddress(), account.getEmail().getAddress());
        assertEquals(account.getPassword(), account.getPassword());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    public void account_save_duplicated() {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(true);

        assertThrows(EmailDuplicationException.class, () -> accountSaveService.save(signUpRequest));
    }

}
