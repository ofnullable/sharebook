package org.slam.publicshare.account.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.domain.Email;
import org.slam.publicshare.account.dto.SignUpRequest;
import org.slam.publicshare.account.exception.EmailDuplicationException;
import org.slam.publicshare.account.repository.AccountRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.account.utils.AccountUtils.buildNormalSignUpRequest;

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
