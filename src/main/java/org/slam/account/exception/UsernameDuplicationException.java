package org.slam.account.exception;

import org.slam.account.domain.Email;

public class EmailDuplicationException extends RuntimeException {
    private Email email;
    public EmailDuplicationException(Email username) {
        this.email = username;
    }
}
