package me.ofnullable.sharebook.book.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.exception.NoSuchAccountException;
import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.dto.book.SaveBookRequest;
import me.ofnullable.sharebook.book.exception.NoSuchBookException;
import me.ofnullable.sharebook.book.exception.NoSuchCategoryException;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.book.service.BookSaveService;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static me.ofnullable.sharebook.book.utils.BookUtils.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class BookControllerTest extends WithAuthenticationPrincipal {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookSaveService bookSaveService;

    @Mock
    private BookFindService bookFindService;

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = super.setup(bookController);
    }

    private Book book = buildBook();

    @Test
    @DisplayName("도서 Id로 도서 조회")
    void find_book_by_id() throws Exception {
        given(bookFindService.findById(any(Long.class)))
                .willReturn(book);

        mvc.perform(get("/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 도서 조회 - 404")
    void find_book_by_invalid_id() throws Exception {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(NoSuchBookException.class);

        mvc.perform(get("/book/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("도서 등록")
    void save_book() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willReturn(book);

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("존재하지 않는 계정으로 도서 등록하는 경우 - 404")
    void save_book_with_invalid_account() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willThrow(NoSuchAccountException.class);

        mvc.perform(post("/book")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("No such account")));
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 도서 등록하는 경우 - 404")
    void save_book_with_invalid_category() throws Exception {
        given(bookSaveService.save(any(SaveBookRequest.class), any(Account.class)))
                .willThrow(NoSuchCategoryException.class);

        mvc.perform(post("/book")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildSaveBookRequest())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("No such category")));
    }

    @Test
    @DisplayName("도서 리스트 요청")
    void book_list_pagination() throws Exception {
        given(bookFindService.findAll(any(), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("도서 제목 검색 요청 및 한글 테스트")
    void book_list_pagination_by_title() throws Exception {
        given(bookFindService.findAll(any(String.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=10&searchText=카테고리"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비정상적인 사이즈로 요청 시 요청 사이즈 20으로 고정")
    void book_list_irregular_size_pagination() throws Exception {
        given(bookFindService.findAll(any(), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/books?page=1&size=500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(20)));
    }

    @Test
    @DisplayName("카테고리별 도서 조회")
    void find_book_by_category() throws Exception {
        given(bookFindService.findAllByCategory(any(String.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        // path variable에 한글
        mvc.perform(get("/books/category/운영체제?page=1&size=10")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 조회하는 경우 - 404")
    void find_book_by_invalid_category() throws Exception {
        given(bookFindService.findAllByCategory(any(String.class), any(PageRequest.class)))
                .willThrow(NoSuchCategoryException.class);

        // path variable에 한글
        mvc.perform(get("/books/category/카테고리?page=1&size=10")
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("특정 유저가 등록한 도서 리스트 요청")
    void find_book_by_owner() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/account/1/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 유저가 등록한 도서 리스트 요청")
    void find_book_by_invalid_owner() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(Page.empty());

        mvc.perform(get("/account/11/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("현재 로그인 한 유저가 등록한 도서 리스트 요청")
    void find_book_by_logged_in_account() throws Exception {
        given(bookFindService.findAllByOwner(any(Long.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/account/0/books?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 상태의 내 도서 요청")
    void find_book_by_lending_status() throws Exception {
        given(bookFindService.findAllMyBookByLendingStatus(any(Long.class), any(LendingStatus.class), any(PageRequest.class)))
                .willReturn(buildNormalPageBook());

        mvc.perform(get("/account/books/lending/REQUESTED?page=1&size=20"))
                .andExpect(status().isOk());
    }

}
