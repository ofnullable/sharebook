package org.slam.publicshare.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slam.publicshare.rental.domain.RentalStatus;
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
public class RentalIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("특정 도서의 대여목록 조회")
    public void get_rental_by_book_id() throws Exception {
        var resultAction = mvc.perform(get("/book/1/rental"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 도서의 대여목록 조회")
    public void get_rental_by_invalid_book_id() throws Exception {
        var resultAction = mvc.perform(get("/book/0/rental"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("로그인 후 현재 계정 대여기록 요청")
    public void find_rental_by_account_id_with_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/rental"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인하지 않고 현재 계정 대여기록 요청")
    public void find_rental_by_account_id_with_no_auth() throws Exception {
        var resultAction = mvc.perform(get("/account/rental"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test2@asd.com")
    @DisplayName("로그인 후 대여요청")
    public void save_rental_with_auth() throws Exception {
        var resultAction = mvc.perform(post("/book/5/rental"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 하지 않고 대여요청")
    public void save_rental_with_no_auth() throws Exception {
        var resultAction = mvc.perform(post("/book/1/rental"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("ACCEPTED로 대여기록 업데이트")
    public void update_rental_to_accepted() throws Exception {
        var resultAction = mvc.perform(put("/rental/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.ACCEPTED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("REJECTED로 대여기록 업데이트")
    public void update_rental_to_rejected() throws Exception {
        var resultAction = mvc.perform(put("/rental/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.REJECTED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails("test1@asd.com")
    @DisplayName("RETURNED로 대여기록 업데이트")
    public void update_rental_to_returned() throws Exception {
        var resultAction = mvc.perform(put("/rental/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.RETURNED)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
