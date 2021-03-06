package me.ofnullable.sharebook.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
                .orElseThrow(() -> new IllegalArgumentException("잘못된 도서상태코드 입니다. 코드: " + code));
    }

}
