package me.ofnullable.sharebook.lending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class LendingIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("로그인 후 현재 계정 대여기록 요청")
    void find_lending_by_account_id_with_auth() throws Exception {
        mvc.perform(get("/lendings/REQUESTED?page=1&size=20"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인하지 않고 현재 계정 대여기록 요청")
    void find_lending_by_account_id_with_no_auth() throws Exception {
        mvc.perform(get("/lendings/REQUESTED?page=1&size=20"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("로그인 후 특정 도서의 최근 대여기록 요청")
    void find_latest_lending_by_book_id_with_auth() throws Exception {
        mvc.perform(get("/lending/book/65/latest"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인하지 않고 특정 도서의 최근 대여기록 요청")
    void find_latest_lending_by_book_id_with_no_auth() throws Exception {
        mvc.perform(get("/lending/book/1/latest"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("로그인 후 대여요청")
    void save_lending_with_auth() throws Exception {
        mvc.perform(post("/lending/book/5"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 하지 않고 대여요청")
    void save_lending_with_no_auth() throws Exception {
        mvc.perform(post("/lending/book/1"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("ACCEPTED로 대여기록 업데이트")
    void update_lending_to_accepted() throws Exception {
        mvc.perform(put("/lending/6/ACCEPTED"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test2@asd.com")
    @DisplayName("CANCELED로 대여기록 업데이트")
    void update_lending_to_canceled() throws Exception {
        mvc.perform(put("/lending/7/CANCELED"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("REJECTED로 대여기록 업데이트")
    void update_lending_to_rejected() throws Exception {
        mvc.perform(put("/lending/8/REJECTED"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("RETURNED로 대여기록 업데이트")
    void update_lending_to_returned() throws Exception {
        mvc.perform(put("/lending/3/RETURNED"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
