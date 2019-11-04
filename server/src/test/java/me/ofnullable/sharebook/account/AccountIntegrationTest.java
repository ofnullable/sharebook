package me.ofnullable.sharebook.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.config.security.userdetails.SignInRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static me.ofnullable.sharebook.account.utils.AccountUtils.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AccountIntegrationTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Account defaultAccount = buildNormalAccount();

    @Test
    @DisplayName("로그인 후 현재 계정 조회")
    @WithUserDetails("test1@asd.com")
    void get_current_account_with_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/0"))
                .andExpect(status().isOk())
                .andDo(print());

        assertEmailAndName(resultAction);
    }

    @Test
    @DisplayName("로그인 하지않고 현재 계정 조회")
    void get_current_account_with_no_auth() throws Exception {
        mvc.perform(get("/account/0"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 계정 조회")
    @WithUserDetails("test1@asd.com")
    void get_account_with_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andDo(print());

        assertEmailAndName(resultAction);
    }

    @Test
    @DisplayName("로그인하지 않고 계정 조회 - 401")
    void get_account_with_no_auth() throws Exception {
        mvc.perform(get("/account/1"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("정상적인 로그인 요청")
    void sign_in() throws Exception {
        var signInRequest = SignInRequest.builder()
                .username("test1@asd.com")
                .password("test")
                .build();

        var resultAction = mvc.perform(post("/auth/sign-in")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signInRequest))
        )
                .andExpect(status().isOk())
                .andDo(print());

        assertEmailAndName(resultAction);
    }

    @Test
    @DisplayName("존재하지 않는 계정으로 로그인 요청 - 400")
    void sign_in_with_bad_credential() throws Exception {
        var signInRequest = SignInRequest.builder()
                .username("invalidUsername")
                .password("test")
                .build();

        mvc.perform(post("/auth/sign-in")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signInRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Username and/or Password did not match")))
                .andExpect(jsonPath("$.path", is("/auth/sign-in")))
                .andDo(print());
    }

    @Test
    @DisplayName("데이터를 전송하지 않은 로그인 요청 - 400")
    void sign_in_with_null_credential() throws Exception {
        var signInRequest = SignInRequest.builder()
                .username(null)
                .password(null)
                .build();

        mvc.perform(post("/auth/sign-in")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signInRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Username and/or Password did not match")))
                .andExpect(jsonPath("$.path", is("/auth/sign-in")))
                .andDo(print());
    }

    @Test
    @DisplayName("유효하지 않은 데이터로 로그인 요청 - 400")
    void sign_in_with_invalid_parameter() throws Exception {
        var signUpRequest = SignUpRequest.builder()
                .email(null)
                .password(null)
                .build();

        mvc.perform(post("/auth/sign-in")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Username and/or Password did not match")))
                .andExpect(jsonPath("$.path", is("/auth/sign-in")))
                .andDo(print());
    }

    @Test
    @DisplayName("정상적인 회원가입 요청")
    void sign_up() throws Exception {
        var signUpRequest = buildNormalSignUpRequest("test@test.com");

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 요청 - 409")
    void sign_up_with_duplicated_email() throws Exception {
        var signUpRequest = buildNormalSignUpRequest("test1@asd.com");

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
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
    void sign_up_with_invalid_email() throws Exception {
        var signUpRequest = buildInvalidSignUpRequest();

        mvc.perform(post("/account")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUpRequest))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is("Invalid value")))
                .andExpect(jsonPath("$.path", is("/account")))
                .andExpect(jsonPath("$.errors['email.address']", is("must be a well-formed email address")))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test1@asd.com", roles = "BASIC")
    @DisplayName("로그인 후 비밀번호 업데이트")
    void update_password_with_auth() throws Exception {
        mvc.perform(patch("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("test")
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test1@asd.com", roles = "BASIC")
    @DisplayName("로그인 후 존재하지 않는 계정 비밀번호 업데이트")
    void update_password_with_invalid_account_with_auth() throws Exception {
        mvc.perform(patch("/account/10")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("newPassword")
        )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인하지 않고 비밀번호 업데이트")
    void update_password_with_no_auth() throws Exception {
        mvc.perform(patch("/account/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("newPassword")
        )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    private void assertEmailAndName(ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.email", is(defaultAccount.getEmail().getAddress())))
                .andExpect(jsonPath("$.name", is(defaultAccount.getName())));
    }

}
