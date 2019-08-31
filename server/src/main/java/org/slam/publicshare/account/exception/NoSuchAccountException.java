package org.slam.publicshare.account.exception;

import lombok.Getter;

@Getter
public class NoSuchAccountException extends RuntimeException {
    private Long id;
    private String username;
    public NoSuchAccountException(Long id) {
        this.id = id;
    }
    public NoSuchAccountException(String username) {
        this.username = username;
    }
}
