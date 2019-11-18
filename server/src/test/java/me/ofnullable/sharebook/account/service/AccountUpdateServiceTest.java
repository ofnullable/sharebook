package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountUpdateServiceTest {

    @InjectMocks
    private AccountUpdateService accountUpdateService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final Account account = Account.builder().email(Email.of("test@asd.com")).name("test").password("test").build();

    @Test
    @DisplayName("비밀번호 업데이트")
    void update_password() {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);
        given(passwordEncoder.encode(any(String.class)))
                .willReturn("test1");

        var result = accountUpdateService.updatePassword(1L, "test1");

        then(result.getEmail().getAddress())
                .isEqualTo(account.getEmail().getAddress());
        then(result.getPassword())
                .isEqualTo("test1");
    }

}
