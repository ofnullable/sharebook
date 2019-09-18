package org.slam.publicshare.book.exception;

import lombok.Getter;

@Getter
public class NoSuchBookStatusException extends RuntimeException {
    private int code;
    public NoSuchBookStatusException(int code) {
        this.code = code;
    }
}
