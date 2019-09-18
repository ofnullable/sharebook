package org.slam.publicshare.book.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.account.exception.NoSuchAccountException;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.exception.NoSuchCategoryException;
import org.slam.publicshare.book.repository.BookRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.account.utils.AccountUtils.buildNormalAccount;
import static org.slam.publicshare.book.utils.BookUtils.*;
import static org.slam.publicshare.book.utils.CategoryUtils.buildCategory;

@ExtendWith(SpringExtension.class)
public class BookSaveServiceTest {

    @InjectMocks
    private BookSaveService bookSaveService;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private CategoryFindService categoryFindService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("도서 등록")
    public void save_book() {
        var book = buildBook();

        given(accountFindService.findById(any(Long.class)))
                .willReturn(buildNormalAccount());
        given(categoryFindService.findByName(any(String.class)))
                .willReturn(buildCategory());
        given(bookRepository.save(any(Book.class)))
                .willReturn(book);

        var result = bookSaveService.save(buildSaveBookRequest(), buildNormalAccount());

        equalBook(result, book);
    }

    @Test
    @DisplayName("존재하지 카테고리로 도서 등록하는 경우 - NoSuchCategoryException")
    public void save_book_with_invalid_category() {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(buildNormalAccount());
        given(categoryFindService.findByName(any(String.class)))
                .willThrow(NoSuchCategoryException.class);

        assertThrows(NoSuchCategoryException.class, () -> bookSaveService.save(buildSaveBookRequest(), buildNormalAccount()));
    }

}
