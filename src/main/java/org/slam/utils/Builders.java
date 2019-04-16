package org.slam.utils;

import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class Builders {

    public static Book buildBook(@NonNull Long id, @Nullable BookStatus status, @NonNull String modifier) {
        return Book.builder()
                .id(id)
                .status(status)
                .modifiedBy(modifier)
                .build();
    }

    public static BookHistory buildHistory(@NonNull Long bookId, @Nullable BookStatus status, @NonNull String modifier) {
        return BookHistory.builder()
                .bookId(bookId)
                .requestedStatus(status)
                .modifiedBy(modifier)
                .build();
    }

    public static BookHistory buildHistory(@NonNull Book book, @NonNull BookStatus status) {
        return BookHistory.builder()
                .bookId(book.getId())
                .id(book.getHistories().get(0).getId())
                .modifiedBy(book.getModifiedBy())
                .requestedStatus(status)
                .build();
    }

}
