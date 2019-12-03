package me.ofnullable.sharebook.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.exception.PasswordNotMatchingException;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.account.service.AccountSaveService;
import me.ofnullable.sharebook.account.service.AccountUpdateService;
import me.ofnullable.sharebook.account.service.AccountVerifyService;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static me.ofnullable.sharebook.account.utils.AccountUtils.*;
import static me.ofnullable.sharebook.file.utils.StorageUtils.getMultipartFile;
import static me.ofnullable.sharebook.utils.RequestBuilderUtils.putMultipart;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class AccountControllerTest extends WithAuthenticationPrincipal {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private AccountSaveService accountSaveService;

    @Mock
    private AccountUpdateService accountUpdateService;

    @Mock
    private AccountVerifyService accountVerifyService;

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mvc = super.setup(accountController);
    }

    private Account account = buildNormalAccount();

    @Test
    @DisplayName("현재 세션에 있는 계정조회")
    void get_my_account() throws Exception {
        mvc.perform(get("/account/0"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("계정 Id로 계정 조회")
    void find_account_by_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);

        mvc.perform(get("/account/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정조회 - 404")
    void find_account_by_invalid_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(ResourceNotFoundException.class);

        mvc.perform(get("/account/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("이메일을 가진 계정이 존재하는경우 true")
    void is_account_duplicated_true() throws Exception {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(true);

        mvc.perform(get("/account/duplicate?email=test@asd.com"))
                .andExpect(content().string("true"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("이메일을 가진 계정이 존재하지 않는 경우 false")
    void is_account_duplicated_false() throws Exception {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(false);

        mvc.perform(get("/account/duplicate?email=test@asd.com"))
                .andExpect(content().string("false"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("올바른 이메일 주소가 아닌 경우 - 400")
    void is_account_duplicated() throws Exception {
        given(accountFindService.existedEmail(any(Email.class)))
                .willReturn(true);

        mvc.perform(get("/account/duplicate?email=test@asd"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("유효한 이메일로 회원가입")
    void sign_in() throws Exception {
        given(accountSaveService.saveAndSignIn(any(SignUpRequest.class)))
                .willReturn(buildNormalAccount());

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildNormalSignUpRequest("test1@asd.com")))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    void sign_in_duplicated_email() throws Exception {
        given(accountSaveService.saveAndSignIn(any(SignUpRequest.class)))
                .willThrow(EmailDuplicationException.class);

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildNormalSignUpRequest("test1@asd.com")))
        )
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("유효하지 않은 이메일로 회원가입 - 400")
    void sign_in_invalid_email() throws Exception {
        given(accountSaveService.saveAndSignIn(any(SignUpRequest.class)))
                .willReturn(buildNormalAccount());

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildInvalidSignUpRequest()))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("계정정보 업데이트 요청")
    void update_password() throws Exception {
        var dto = buildUpdateDto("테스트", "test");

        given(accountUpdateService.update(any(UpdateAccountRequest.class)))
                .willReturn(account);

        mvc.perform(put("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정정보 업데이트 요청 - 404")
    void invalid_update_password() throws Exception {
        var dto = buildUpdateDto("없는계정", "test");

        given(accountUpdateService.update(any(UpdateAccountRequest.class)))
                .willThrow(ResourceNotFoundException.class);

        mvc.perform(put("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("비밀번호 인증")
    void verify_with_valid_password() throws Exception {
        account.verified();
        given(accountVerifyService.verify(any(Account.class), any(String.class)))
                .willReturn(account);

        mvc.perform(post("/account/verify")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("일치하지 않는 비밀번호로 인증 요청 - 400")
    void verify_with_invalid_password() throws Exception {
        given(accountVerifyService.verify(any(Account.class), any(String.class)))
                .willThrow(PasswordNotMatchingException.class);

        mvc.perform(post("/account/verify")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("아바타(프로필) 업데이트")
    void update_avatar() throws Exception {
        var accountWithAvatar = buildAccountWithAvatar();

        given(accountUpdateService.updateAvatar(any(Long.class), any(MultipartFile.class)))
                .willReturn(accountWithAvatar);

        mvc.perform(putMultipart("/account/avatar")
                .file(getMultipartFile("avatar"))
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아바타(프로필) 업로드 오류 - 500")
    void upload_avatar_failure() throws Exception {
        given(accountUpdateService.updateAvatar(any(Long.class), any(MultipartFile.class)))
                .willThrow(IOException.class);

        mvc.perform(putMultipart("/account/avatar")
                .file(getMultipartFile("avatar"))
        )
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("존재하지 않는 계정 정보 업데이트 - 404")
    void update_avatar_failure() throws Exception {
        given(accountUpdateService.updateAvatar(any(Long.class), any(MultipartFile.class)))
                .willThrow(ResourceNotFoundException.class);

        mvc.perform(putMultipart("/account/avatar")
                .file(getMultipartFile("avatar"))
        )
                .andExpect(status().isNotFound());
    }

}
