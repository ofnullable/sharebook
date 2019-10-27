package me.ofnullable.sharebook.book.exception;

import lombok.Getter;

@Getter
public class NoSuchBookException extends RuntimeException {
    private Long id;
    public NoSuchBookException(Long id) {
        this.id = id;
    }
}
