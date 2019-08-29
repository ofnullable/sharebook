package org.slam.publicshare.utils;

import org.slam.publicshare.dto.book.Book;
import org.slam.publicshare.dto.book.BookHistory;
import org.slam.publicshare.dto.book.BookStatus;
import org.springframework.lang.NonNull;

public class Builders {

    public static Book buildBook(@NonNull Book book, @NonNull BookStatus toBe) {
        return Book.builder()
                .id(book.getId())
                .status(toBe)
                .modifiedBy(book.getModifiedBy())
                .build();
    }

    public static BookHistory buildHistory(@NonNull Book book) {
        return BookHistory.builder()
                .bookId(book.getId())
                .requestedStatus(book.getStatus())
                .modifiedBy(book.getModifiedBy())
                .build();
    }

    public static BookHistory buildHistoryWithHistoryId(@NonNull Book book) {
        return BookHistory.builder()
                .id(book.getRequestedHistoryId())
                .bookId(book.getId())
                .requestedStatus(book.getStatus())
                .modifiedBy(book.getModifiedBy())
                .build();
    }

    public static BookHistory buildHistoryWithHistoryId(@NonNull Book book, @NonNull BookStatus toBe) {
        return BookHistory.builder()
                .id(book.getRequestedHistoryId())
                .bookId(book.getId())
                .requestedStatus(toBe)
                .modifiedBy(book.getModifiedBy())
                .build();
    }

}
