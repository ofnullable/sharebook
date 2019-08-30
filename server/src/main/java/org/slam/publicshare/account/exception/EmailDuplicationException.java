package org.slam.publicshare.account.exception;

import lombok.Getter;
import org.slam.publicshare.account.domain.Email;

@Getter
public class EmailDuplicationException extends RuntimeException {
    private Email email;
    public EmailDuplicationException(Email email) {
        this.email = email;
    }
}
