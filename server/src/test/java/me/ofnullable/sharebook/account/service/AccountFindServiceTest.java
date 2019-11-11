package me.ofnullable.sharebook.account.service;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.repository.AccountRepository;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildNormalAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountFindServiceTest {

    @InjectMocks
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;

    private Account account = buildNormalAccount();

    @Test
    @DisplayName("SECURITY - 이메일로 조회")
    void load_account_by_email() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willReturn(Optional.of(account));

        // when
        var result = accountFindService.loadUserByUsername("test@test.com");

        assertEquals(account.getEmail().getAddress(), result.getUsername());
        assertEquals(account.getPassword(), result.getPassword());
    }

    @Test
    @DisplayName("SECURITY - 이메일이 존재하지 않는 경우 UsernameNotFoundException")
    void load_account_by_username_with_invalid_email() {
        given(accountRepository.findByEmail(any(Email.class)))
                .willReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> accountFindService.loadUserByUsername("test@test.com"));
    }

    @Test
    @DisplayName("아이디(PK)로 조회")
    void find_account_by_id() {
        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.of(account));

        var result = accountFindService.findById(1L);

        accountEquals(result);
    }

    @Test
    @DisplayName("아이디(PK)가 존재하지 않는 경우 ResourceNotFoundException")
    void find_account_by_id_with_invalid_id() {
        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountFindService.findById(1L));
    }

    @Test
    @DisplayName("계정정보가 없는 경우 빈 리스트")
    void find_account_all() {
        given(accountRepository.findAll())
                .willReturn(Collections.emptyList());

        var result = accountFindService.findAll();
        assertEquals(0, result.size());
    }

    private void accountEquals(Account result) {
        assertEquals(account.getName(), result.getName());
        assertEquals(account.getEmail().getAddress(), result.getEmail().getAddress());
        assertEquals(account.getPassword(), result.getPassword());
    }

}
