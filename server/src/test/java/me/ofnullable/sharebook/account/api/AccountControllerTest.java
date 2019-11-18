package me.ofnullable.sharebook.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.exception.PasswordNotMatchingException;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.account.service.AccountSaveService;
import me.ofnullable.sharebook.account.service.AccountUpdateService;
import me.ofnullable.sharebook.account.service.AccountVerifyService;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import me.ofnullable.sharebook.error.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static me.ofnullable.sharebook.account.utils.AccountUtils.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(jsonPath("$.message", is(ErrorCode.INVALID_INPUT_VALUE.getMessage())))
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
    @DisplayName("비밀번호 변경 요청")
    void update_password() throws Exception {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willReturn(account);

        mvc.perform(patch("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("newPassword")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정 비밀번호 변경 요청 - 404")
    void invalid_update_password() throws Exception {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willThrow(ResourceNotFoundException.class);

        mvc.perform(patch("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("newPassword")
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
                .content("test"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("일치하지 않는 비밀번호로 인증 요청 - 400")
    void verify_with_invalid_password() throws Exception {
        given(accountVerifyService.verify(any(Account.class), any(String.class)))
                .willThrow(PasswordNotMatchingException.class);

        mvc.perform(post("/account/verify")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test"))
                .andExpect(status().isBadRequest());
    }

}
