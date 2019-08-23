package org.slam.account.exception;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {
    private Long id;
    private String username;
    public AccountNotFoundException(Long id) {
        this.id = id;
    }
    public AccountNotFoundException(String username) {
        this.username = username;
    }
}
