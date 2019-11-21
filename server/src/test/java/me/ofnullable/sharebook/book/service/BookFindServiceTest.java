package me.ofnullable.sharebook.book.service;

import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.repository.BookRepository;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static me.ofnullable.sharebook.book.utils.BookUtils.*;
import static me.ofnullable.sharebook.book.utils.CategoryUtils.buildCategory;
import static me.ofnullable.sharebook.utils.PageRequestUtils.buildPageRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class BookFindServiceTest {

    @InjectMocks
    private BookFindService bookFindService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryFindService categoryFindService;

    private final Book book = buildBook();

    @Test
    @DisplayName("도서 Id로 도서 조회")
    void find_book_by_id() {
        given(bookRepository.findById(any(Long.class)))
                .willReturn(Optional.of(book));

        var result = bookFindService.findById(any(Long.class));

        equalBook(result, book);
    }

    @Test
    @DisplayName("도서가 존재하지 않는 경우 - ResourceNotFoundException")
    void find_book_by_invalid_id() {
        given(bookRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookFindService.findById(any(Long.class)));
    }

    @Test
    @DisplayName("도서 리스트 요청")
    void book_list_pagination() {
        given(bookRepository.findAllByOrderByStatus(any(Pageable.class)))
                .willReturn(buildNormalPageBook());

        var result = bookFindService.findAll(null, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
        assertEquals(result.getSize(), 20);
    }

    @Test
    @DisplayName("도서 제목 검색 요청")
    void book_list_pagination_by_title() {
        given(bookRepository.findALlByTitleContainingIgnoreCaseOrderByStatus(any(String.class), any(Pageable.class)))
                .willReturn(buildNormalPageBook());

        var result = bookFindService.findAll("test title", buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
        assertEquals(result.getSize(), 20);
    }

    @Test
    @DisplayName("비정상적인 사이즈로 요청 시 요청 사이즈 20으로 고정")
    void book_list_irregular_size_pagination() {
        given(bookRepository.findALlByTitleContainingIgnoreCaseOrderByStatus(any(String.class), any(Pageable.class)))
                .willReturn(buildIrregularPageBook());

        var result = bookFindService.findAll("test title", buildPageRequest(100));

        assertEquals(result.getTotalElements(), 3);
        assertEquals(result.getSize(), 20);
    }

    @Test
    @DisplayName("카테고리 내 도서 요청")
    void find_book_by_category() {
        given(categoryFindService.findCategoryById(any(Long.class)))
                .willReturn(buildCategory());
        given(bookRepository.findAllByCategoryNameOrderByStatus(any(String.class), any(Pageable.class)))
                .willReturn(buildNormalPageBook());

        var result = bookFindService.findAllByCategory("운영체제", buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
        assertEquals(result.getSize(), 20);
    }

    @Test
    @DisplayName("특정 유저가 등록한 도서 리스트 요청")
    void find_book_by_author() {
        given(bookRepository.findAllByOwnerId(any(Long.class), any(Pageable.class)))
                .willReturn(buildNormalPageBook());

        var result = bookFindService.findAllByOwner(1L, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
        assertEquals(result.getSize(), 20);
    }

    @Test
    @DisplayName("존재하지 않는 유저가 등록한 도서 리스트 요청")
    void find_book_by_invalid_author() {
        given(bookRepository.findAllByOwnerId(any(Long.class), any(Pageable.class)))
                .willReturn(Page.empty());

        var result = bookFindService.findAllByOwner(11L, buildPageRequest(20));

        assertEquals(result.getSize(), 0);
    }

}
