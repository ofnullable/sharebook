package org.slam.account.exception;

import lombok.Getter;
import org.slam.account.domain.Email;

@Getter
public class EmailDuplicationException extends RuntimeException {
    private Email email;
    public EmailDuplicationException(Email email) {
        this.email = email;
    }
}
