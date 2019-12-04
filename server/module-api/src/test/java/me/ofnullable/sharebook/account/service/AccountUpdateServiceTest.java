package me.ofnullable.sharebook.account.service;

import me.ofnullable.file.service.FileStorageService;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;

import static me.ofnullable.sharebook.account.utils.AccountUtils.*;
import static me.ofnullable.sharebook.utils.StorageTestUtils.getMultipartFile;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AccountUpdateServiceTest {

    @InjectMocks
    private AccountUpdateService accountUpdateService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityContext securityContext;

    private final Account account = Account.builder().email(Email.of("test@asd.com")).name("test").password("test").build();
    private final MockMultipartFile multipartFile = getMultipartFile("test.jpg");

    @Test
    @DisplayName("계정정보 업데이트")
    void update_account() {
        var dto = buildUpdateDto("테스트", "test1");

        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);
        given(passwordEncoder.encode(any(String.class)))
                .willReturn("test1");
        given(securityContext.getAuthentication())
                .willReturn(getAuthentication(account));

        SecurityContextHolder.setContext(securityContext);

        var result = accountUpdateService.update(dto);

        then(result.getName())
                .isEqualTo(dto.getName());
        then(result.getPassword())
                .isEqualTo(dto.getNewPassword());
    }

    @Test
    @DisplayName("아바타(프로필) 업데이트")
    void update_avatar() throws IOException {
        var account = buildAccountWithAvatar();

        given(fileStorageService.store(any(InputStream.class), any(String.class)))
                .willReturn("/image/test.jpg");
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);

        given(securityContext.getAuthentication())
                .willReturn(getAuthentication(account));

        SecurityContextHolder.setContext(securityContext);

        var result = accountUpdateService.updateAvatar(1L, multipartFile);

        assertEquals(result.getAvatar(), "/image/test.jpg");
        assertTrue(account.isVerified());
    }

    @Test
    @DisplayName("아바타(프로필) 업로드 실패 시 - IOException")
    void upload_avatar_failure() throws IOException {
        given(fileStorageService.store(any(InputStream.class), any(String.class)))
                .willThrow(IOException.class);

        var result = catchThrowable(() -> accountUpdateService.updateAvatar(1L, multipartFile));

        then(result)
                .isInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("아이디(PK)가 존재하지 않는 경우 ResourceNotFoundException")
    void update_avatar_failure() throws IOException {
        given(fileStorageService.store(any(InputStream.class), any(String.class)))
                .willReturn("/image/test.jpg");
        given(accountFindService.findById(any(Long.class)))
                .willThrow(ResourceNotFoundException.class);

        var result = catchThrowable(() -> accountUpdateService.updateAvatar(1L, multipartFile));

        then(result)
                .isInstanceOf(ResourceNotFoundException.class);
    }

}
