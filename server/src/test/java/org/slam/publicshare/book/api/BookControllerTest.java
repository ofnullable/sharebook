package org.slam.publicshare.book.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.exception.NoSuchAccountException;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.dto.book.SaveBookRequest;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.exception.NoSuchCategoryException;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.book.service.BookSaveService;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.config.WithAuthenticationPrincipal;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.book.utils.BookUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class BookControllerTest extends WithAuthenticationPrincipal {

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookSaveService bookSaveService;

    @Mock
    private BookFindService bookFindService;

    @BeforeEach
    public void setup() {
        mvc = super.setup(bookController);
    }

    private Book book = buildBook();

    @Test
    @DisplayName("도서 Id로 도서 조회")
    public void find_book_by_id() throws Exception {
        given(bookFindService.findById(any(Long.class)))
                .willReturn(book);

        mvc.perform(get("/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 도서 조회 - 404")
    public void find_book_by_invalid_id() throws Exception {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(NoSuchBookException.class);

        mvc.perform(get("/book/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("도서 등록")
    public void save_book() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willReturn(book);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("존재하지 않는 계정으로 도서 등록하는 경우 - 404")
    public void save_book_with_invalid_account() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willThrow(NoSuchAccountException.class);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("No such account")));
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 도서 등록하는 경우 - 404")
    public void save_book_with_invalid_category() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willThrow(NoSuchCategoryException.class);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("No such category")));
    }

    @Test
    @DisplayName("도서 리스트 요청")
    public void book_list_pagination() throws Exception {
        given(bookFindService.findAll(any(), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("도서 제목 검색 요청 및 한글 테스트")
    public void book_list_pagination_by_title() throws Exception {
        given(bookFindService.findAll(any(String.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=10&searchText=카테고리"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비정상적인 사이즈로 요청시 요청 사이즈 20으로 고정")
    public void book_list_irregular_size_pagination() throws Exception {
        given(bookFindService.findAll(any(), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(20)));
    }

    @Test
    @DisplayName("카테고리별 도서 조회")
    public void find_book_by_category() throws Exception {
        given(bookFindService.findAllByCategory(any(String.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        // path variable에 한글
        mvc.perform(get("/books/category/운영체제?page=1&size=10")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 조회하는 경우 - 404")
    public void find_book_by_invalid_category() throws Exception {
        given(bookFindService.findAllByCategory(any(String.class), any(PageRequest.class)))
                .willThrow(NoSuchCategoryException.class);

        // path variable에 한글
        mvc.perform(get("/books/category/카테고리?page=1&size=10")
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("특정 유저가 등록한 도서 리스트 요청")
    public void find_book_by_owner() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/account/1/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 유저가 등록한 도서 리스트 요청")
    public void find_book_by_invalid_owner() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(buildEmptyPageBook());

        mvc.perform(get("/account/11/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("현재 로그인 한 유저가 등록한 도서 리스트 요청")
    public void find_book_by_logged_in_account() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/account/0/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

}
