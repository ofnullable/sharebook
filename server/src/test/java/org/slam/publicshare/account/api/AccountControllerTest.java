package org.slam.publicshare.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.dto.SignUpRequest;
import org.slam.publicshare.account.exception.AccountNotFoundException;
import org.slam.publicshare.account.exception.EmailDuplicationException;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.account.service.AccountSaveService;
import org.slam.publicshare.account.service.AccountUpdateService;
import org.slam.publicshare.error.ApiErrorHandler;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.testutil.AccountUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

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
        this.mvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .setControllerAdvice(ApiErrorHandler.class)
                .build();
    }

    private Account account = buildNormalAccount();

    @Test
    @DisplayName("존재하는 계정조회")
    public void find_account_by_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);

        mvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 계정조회 - 404")
    public void find_account_by_id_failure() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(AccountNotFoundException.class);

        mvc.perform(get("/account/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
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
                .andExpect(status().isCreated())
                .andDo(print());
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
                .andExpect(status().isConflict())
                .andDo(print());
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
                .andExpect(status().isBadRequest())
                .andDo(print());
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
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 계정 비밀번호 변경 요청 - 404")
    public void invalid_update_password() throws Exception {
        given(accountUpdateService.updatePassword(any(Long.class), anyString()))
                .willThrow(AccountNotFoundException.class);

        mvc.perform(patch("/account/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("newPassword")
        )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
