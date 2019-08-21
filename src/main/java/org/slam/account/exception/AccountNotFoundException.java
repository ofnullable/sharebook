package org.slam.account.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Such Account")
public class AccountNotFoundException extends RuntimeException {
    private Long id;
    public AccountNotFoundException(Long id) {
        this.id = id;
    }
}
