package org.slam.publicshare.lending;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slam.publicshare.lending.domain.LendingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LendingIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("로그인 후 현재 계정 대여기록 요청")
    public void find_lending_by_account_id_with_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/lendings/REQUESTED?page=1&size=20"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인하지 않고 현재 계정 대여기록 요청")
    public void find_lending_by_account_id_with_no_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/lendings/REQUESTED"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test2@asd.com")
    @DisplayName("로그인 후 대여요청")
    public void save_lending_with_auth() throws Exception {
        var resultAction = mvc.perform(post("/book/5/lending"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 하지 않고 대여요청")
    public void save_lending_with_no_auth() throws Exception {
        var resultAction = mvc.perform(post("/book/1/lending"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("ACCEPTED로 대여기록 업데이트")
    public void update_lending_to_accepted() throws Exception {
        var resultAction = mvc.perform(put("/lending/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(LendingStatus.ACCEPTED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("REJECTED로 대여기록 업데이트")
    public void update_lending_to_rejected() throws Exception {
        var resultAction = mvc.perform(put("/lending/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(LendingStatus.REJECTED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("RETURNED로 대여기록 업데이트")
    public void update_lending_to_returned() throws Exception {
        var resultAction = mvc.perform(put("/lending/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(LendingStatus.RETURNED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
