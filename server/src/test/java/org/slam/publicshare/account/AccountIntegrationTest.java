package org.slam.publicshare.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.config.security.userdetails.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.slam.publicshare.testutil.AccountUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AccountIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private Account defaultAccount = buildNormalAccount();
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("인증 후 계정 조회")
    @WithMockUser(username = "test1@asd.com", roles = {"BASIC"})
    public void get_account_with_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andDo(print());

        resultMatch(resultAction);
    }

    @Test
    @DisplayName("인증하지 않고 계정 조회 - 401")
    public void get_account_with_no_auth() throws Exception {
        mvc.perform(get("/account/1"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status", is(401)))
                .andExpect(jsonPath("$.message", is("Unauthorized")))
                .andExpect(jsonPath("$.path", is("/account/1")))
                .andDo(print());
    }

    @Test
    @DisplayName("정상적인 로그인 요청")
    public void sign_in_request() throws Exception {
        var signInRequest = SignInRequest.builder()
                .username("test1@asd.com")
                .password("test")
                .build();

        var resultAction = mvc.perform(post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(signInRequest))
        )
                .andExpect(status().isOk())
                .andDo(print());

        resultMatch(resultAction);
    }

    @Test
    @DisplayName("존재하지 않는 계정으로 로그인 요청 - 400")
    public void bad_credentials_sign_in_request() throws Exception {
        var signInRequest = SignInRequest.builder()
                .username("invalidUsername")
                .password("test")
                .build();

        mvc.perform(post("/auth/sign-in")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(signInRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Username and/or Password did not match")))
                .andExpect(jsonPath("$.path", is("/auth/sign-in")))
                .andDo(print());
    }

    @Test
    @DisplayName("정상적인 회원가입 요청")
    public void sign_up_request() throws Exception {
        var signUpRequest = buildNormalSignUpRequest("test@test.com");

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 요청 - 409")
    public void duplicated_sign_up_request() throws Exception {
        var signUpRequest = buildNormalSignUpRequest("test1@asd.com");

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(409)))
                .andExpect(jsonPath("$.message", is("Email duplication")))
                .andExpect(jsonPath("$.path", is("/account")))
                .andDo(print());
    }

    @Test
    @DisplayName("유효하지 않은 이메일로 회원가입 요청 - 400")
    public void invalid_sign_up_request() throws Exception {
        var signUpRequest = buildInvalidSignUpRequest();

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Invalid value")))
                .andExpect(jsonPath("$.path", is("/account")))
                .andExpect(jsonPath("$.errors[0].field", is("email.address")))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test1@asd.com", roles = "BASIC")
    @DisplayName("인증 후 비밀번호 업데이트")
    public void update_password_with_auth() throws Exception {
        mvc.perform(patch("/account/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("test")
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test1@asd.com", roles = "BASIC")
    @DisplayName("인증 후 존재하지 않는 계정 비밀번호 업데이트")
    public void invalid_update_password_with_auth() throws Exception {
        mvc.perform(patch("/account/10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("newPassword")
        )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("인증하지 않고 비밀번호 업데이트")
    public void update_password_with_no_auth() throws Exception {
        mvc.perform(patch("/account/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("newPassword")
        )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    private void resultMatch(ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.email", is(defaultAccount.getEmail().getAddress())))
                .andExpect(jsonPath("$.name", is(defaultAccount.getName())));
    }

}
