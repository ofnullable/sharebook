package org.slam.publicshare.book.exception;

import lombok.Getter;

@Getter
public class NoSuchCategoryException extends RuntimeException {
    private Long id;
    public NoSuchCategoryException(Long id) {
        this.id = id;
    }
}
