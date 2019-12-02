package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Mock
    private SecurityContext securityContext;

    private final Account account = Account.builder().email(Email.of("test@asd.com")).name("test").password("test").build();

    @Test
    @DisplayName("계정정보 업데이트")
    void update_account() {
        var dto = UpdateAccountRequest.builder()
                .id(1L)
                .name("테스트")
                .newPassword("test1")
                .build();

        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);
        given(passwordEncoder.encode(any(String.class)))
                .willReturn("test1");
        given(securityContext.getAuthentication())
                .willReturn(getAuthentication());

        SecurityContextHolder.setContext(securityContext);

        var result = accountUpdateService.update(dto);

        then(result.getName())
                .isEqualTo(dto.getName());
        then(result.getPassword())
                .isEqualTo(dto.getNewPassword());
    }

    private Authentication getAuthentication() {
        var details = new AccountDetails(account);
        return new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
    }

}
