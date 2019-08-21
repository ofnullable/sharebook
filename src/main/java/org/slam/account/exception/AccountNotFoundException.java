package org.slam.account.exception;

import lombok.Getter;
import org.slam.account.domain.Email;

import java.nio.file.AccessDeniedException;

@Getter
public class AccountNotFoundException extends RuntimeException {
    private Long id;
    private Email email;
    public AccountNotFoundException(Long id) {
        this.id = id;
    }
    public AccountNotFoundException(Email email) {
        this.email = email;
    }
}
