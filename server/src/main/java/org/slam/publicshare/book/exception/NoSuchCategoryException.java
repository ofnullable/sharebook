package org.slam.publicshare.book.exception;

import lombok.Getter;

@Getter
public class NoSuchCategoryException extends RuntimeException {
    private String name;
    public NoSuchCategoryException(String name) {
        this.name = name;
    }
}
