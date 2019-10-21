package org.slam.publicshare.lending.utils;

import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.lending.domain.Lending;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slam.publicshare.book.utils.BookUtils.buildBook;
import static org.slam.publicshare.book.utils.BookUtils.equalBook;
import static org.slam.publicshare.common.utils.PageRequestUtils.buildPage;

public class LendingUtils {

    private static Book book = buildBook();

    public static Lending buildLending(Long accountId) {
        return Lending.builder()
                .accountId(accountId)
                .book(book)
                .build();
    }

    public static Lending buildLending(Book book) {
        return Lending.builder()
                .accountId(1L)
                .book(book)
                .build();
    }

    public static Lending buildRequestedLending() {
        var lending = Lending.builder()
                .accountId(1L)
                .book(book)
                .build();
        lending.borrow();
        return lending;
    }

    public static Lending buildAcceptedLending() {
        var lending = Lending.builder()
                .accountId(1L)
                .book(book)
                .build();
        lending.borrow();
        lending.accept();
        return lending;
    }

    public static List<Lending> buildLendingList() {
        return List.of(buildLending(1L), buildLending(2L), buildLending(3L));
    }

    public static Page<Lending> buildPageLending(int size) {
        return buildPage(buildLendingList(), size);
    }

    public static void equalLending(Lending result, Lending target) {
        equalBook(result.getBook(), target.getBook());
        assertEquals(result.getAccountId(), target.getAccountId());
        assertEquals(result.getHistories(), target.getHistories());
    }

}
