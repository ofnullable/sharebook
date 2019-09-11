package org.slam.publicshare.account.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.domain.Email;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class AccountUpdateServiceTest {

    @Mock
    private AccountUpdateService accountUpdateService;

    private Account account = Account.builder().email(Email.of("test@asd.com")).name("test").password("test").build();
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Test
    @DisplayName("비밀번호 업데이트")
    public void update_password() {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willReturn(account);

        var result = accountUpdateService.updatePassword(1L, "test");

        assertTrue(passwordEncoder.matches("test", result.getPassword()));
    }

}
