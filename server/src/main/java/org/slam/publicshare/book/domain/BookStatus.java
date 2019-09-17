package org.slam.publicshare.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slam.publicshare.book.exception.NoSuchBookStatusException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BookStatus {

    AVAILABLE(1, "대여가능"),
    UNAVAILABLE(2, "대여불가");

    private int code;
    private String status;

    public static BookStatus of(int code) {
        return Arrays.stream(BookStatus.values())
                .filter(c -> c.getCode() == code)
                .findAny()
                .orElseThrow(() -> new NoSuchBookStatusException(code));
    }

}
