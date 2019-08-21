package org.slam.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.exception.AccountNotFoundException;
import org.slam.account.repository.AccountRepository;
import org.slam.account.service.AccountFindService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class AccountFindServiceTest {

    @InjectMocks
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;

    private Account account = Account.builder()
            .email(Email.of("test@test.com"))
            .name("test account")
            .password("test")
            .build();

    @Test
    @DisplayName("SECURITY - 이메일이 존재하는 경우 정상작동")
    public void load_account_by_username() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willReturn(Optional.of(account));

        // when
        var result = accountFindService.findByEmail(Email.of("test@test.com"));

        accountEquals(result);
    }

    @Test
    @DisplayName("SECURITY - 이메일이 존재하지 않는 경우 UsernameNotFoundException")
    public void load_account_by_username_failure() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> accountFindService.findByEmail(Email.of("test@test.com")));
    }

    @Test
    @DisplayName("아이디(PK)가 존재하는 경우 정상작동")
    public void find_account_by_id() {
        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.of(account));

        var result = accountFindService.findById(1L);

        accountEquals(result);
    }

    @Test
    @DisplayName("아이디(PK)가 존재하지 않는 경우 AccountNotFoundException")
    public void find_account_by_id_failure() {
        given(accountRepository.findById(any(Long.class)))
                .willThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> accountFindService.findById(1L));
    }

    @Test
    @DisplayName("이메일이 존재하는 경우 정상작동")
    public void find_account_by_email() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willReturn(Optional.of(account));

        var result = accountFindService.findByEmail(Email.of("test@test.com"));

        accountEquals(result);
    }

    @Test
    @DisplayName("이메일이 존재하지 않는 경우 AccountNotFoundException")
    public void find_account_by_email_failure() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> accountFindService.findByEmail(Email.of("test@test.com")));
    }

    private void accountEquals(Account result) {
        assertEquals(account.getName(), result.getName());
        assertEquals(account.getEmail().getAddress(), result.getEmail().getAddress());
        assertEquals(account.getPassword(), result.getPassword());
    }

}
