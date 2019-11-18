package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.exception.PasswordNotMatchingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildNormalAccount;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountVerifyServiceTest {

    @InjectMocks
    private AccountVerifyService accountVerifyService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final Account account = buildNormalAccount();

    @Test
    @DisplayName("비밀번호 인증")
    void verify_with_valid_password() {
        given(passwordEncoder.matches(any(String.class), any(String.class)))
                .willReturn(true);

        var result = accountVerifyService.verify(account, "test");

        then(result.isVerified())
                .isTrue();
    }

    @Test
    @DisplayName("비밀번호 인증 실패시 - PasswordNotMatchingException")
    void verify_with_invalid_password() {
        given(passwordEncoder.matches(any(String.class), any(String.class)))
                .willReturn(false);

        var exception = catchThrowable(() -> accountVerifyService.verify(account, "test"));

        then(exception)
                .isInstanceOf(PasswordNotMatchingException.class);
        then(account.isVerified())
                .isFalse();
    }

}
