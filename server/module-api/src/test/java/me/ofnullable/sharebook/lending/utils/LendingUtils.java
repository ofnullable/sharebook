package me.ofnullable.sharebook.lending.utils;

import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.utils.BookUtils;
import me.ofnullable.sharebook.lending.domain.Lending;
import org.springframework.data.domain.Page;

import java.util.List;

import static me.ofnullable.sharebook.utils.PageRequestUtils.buildPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LendingUtils {

    private static Book book = BookUtils.buildBook();

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

    private static List<Lending> buildLendingList() {
        return List.of(buildLending(1L), buildLending(2L), buildLending(3L));
    }

    public static Page<Lending> buildPageLending(int size) {
        return buildPage(buildLendingList(), size);
    }

    public static void equalLending(Lending result, Lending target) {
        BookUtils.equalBook(result.getBook(), target.getBook());
        assertEquals(result.getBorrowerId(), target.getBorrowerId());
        assertEquals(result.getHistories(), target.getHistories());
    }

}
