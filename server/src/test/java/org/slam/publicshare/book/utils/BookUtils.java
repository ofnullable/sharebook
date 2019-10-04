package org.slam.publicshare.book.utils;

import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.domain.BookStatus;
import org.slam.publicshare.book.domain.Category;
import org.slam.publicshare.book.dto.book.SaveBookRequest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slam.publicshare.account.utils.AccountUtils.buildNormalAccount;
import static org.slam.publicshare.common.utils.PageRequestUtils.buildPage;

public class BookUtils {

    private static Account account = buildNormalAccount();

    private static Book book = Book.builder()
            .title("test book")
            .author("author")
            .publisher("test")
            .description("book for test!")
            .owner(account)
            .imageUrl("/asd")
            .status(BookStatus.AVAILABLE)
            .build();

    private static Category category = Category.of("운영체제");

    public static Book buildBook() {
        book.setCategory(category);
        return book;
    }

    public static SaveBookRequest buildSaveBookRequest() {
        return SaveBookRequest.builder()
                .title("title")
                .author("author")
                .description("description")
                .publisher("publisher")
                .imageUrl("/image/url")
                .categoryId(1L)
                .build();
    }

    public static List<Book> buildBookList() {
        return List.of(book, book, book);
    }

    public static Page<Book> buildNormalPageBook() {
        return buildPage(buildBookList(), 20);
    }

    public static Page<Book> buildEmptyPageBook() {
        return Page.empty();
    }

    public static Page<Book> buildIrregularPageBook() {
        return buildPage(buildBookList(), 100);
    }

    public static void equalBook(Book result, Book target) {
        assertEquals(result.getTitle(), target.getTitle());
        assertEquals(result.getAuthor(), target.getAuthor());
        assertEquals(result.getPublisher(), target.getPublisher());
        assertEquals(result.getDescription(), target.getDescription());
        assertEquals(result.getImageUrl(), target.getImageUrl());
        assertEquals(result.getOwner(), target.getOwner());
        assertEquals(result.getCategory(), target.getCategory());
    }

}
