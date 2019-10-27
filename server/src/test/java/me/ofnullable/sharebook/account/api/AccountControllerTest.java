package me.ofnullable.sharebook.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.exception.NoSuchAccountException;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.account.service.AccountSaveService;
import me.ofnullable.sharebook.account.service.AccountUpdateService;
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

import static me.ofnullable.sharebook.account.utils.AccountUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class AccountControllerTest extends WithAuthenticationPrincipal {

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private AccountSaveService accountSaveService;

    @Mock
    private AccountUpdateService accountUpdateService;

    @BeforeEach
    public void setup() {
        this.mvc = super.setup(accountController);
    }

    private Account account = buildNormalAccount();

    @Test
    @DisplayName("현재 세션에 있는 계정조회")
    public void get_my_account() throws Exception {
        mvc.perform(get("/account/0"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("계정 Id로 계정 조회")
    public void find_account_by_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);

        mvc.perform(get("/account/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정조회 - 404")
    public void find_account_by_invalid_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(NoSuchAccountException.class);

        mvc.perform(get("/account/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("유효한 이메일로 회원가입")
    public void sign_in() throws Exception {
        given(accountSaveService.save(any(SignUpRequest.class)))
                .willReturn(buildNormalAccount());

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildNormalSignUpRequest("test1@asd.com")))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    public void sign_in_duplicated_email() throws Exception {
        given(accountSaveService.save(any(SignUpRequest.class)))
                .willThrow(EmailDuplicationException.class);

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildNormalSignUpRequest("test1@asd.com")))
        )
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("유효하지 않은 이메일로 회원가입 - 400")
    public void sign_in_invalid_email() throws Exception {
        given(accountSaveService.save(any(SignUpRequest.class)))
                .willReturn(buildNormalAccount());

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildInvalidSignUpRequest()))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 변경 요청")
    public void update_password() throws Exception {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willReturn(account);

        mvc.perform(patch("/account/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("newPassword")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정 비밀번호 변경 요청 - 404")
    public void invalid_update_password() throws Exception {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willThrow(NoSuchAccountException.class);

        mvc.perform(patch("/account/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("newPassword")
        )
                .andExpect(status().isNotFound());
    }

}
