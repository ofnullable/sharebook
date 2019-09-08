package org.slam.publicshare.book.exception;

import lombok.Getter;

@Getter
public class NoSuchBookCategoryException extends RuntimeException {
    private String name;
    public NoSuchBookCategoryException(String name) {
        this.name = name;
    }
}
