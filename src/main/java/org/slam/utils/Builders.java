package org.slam.utils;

import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class Builders {

    public static Book buildBook(@NonNull Book book, @NonNull BookStatus toBe) {
        return Book.builder()
                .id(book.getId())
                .status(toBe)
                .modifiedBy(book.getModifiedBy())
                .build();
    }

    public static BookHistory buildHistory(@NonNull Book book, @NonNull BookStatus toBe) {
        return BookHistory.builder()
                .bookId(book.getId())
                .requestedStatus(toBe)
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

    public static BookHistory buildHistoryWithHistoryId(@NonNull Book book, @NonNull BookStatus toBe) {
        return BookHistory.builder()
                .bookId(book.getId())
                .id(book.getHistories().get(0).getId())
                .modifiedBy(book.getModifiedBy())
                .requestedStatus(toBe)
                .build();
    }

}
