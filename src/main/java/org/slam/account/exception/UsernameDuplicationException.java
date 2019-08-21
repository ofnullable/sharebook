package org.slam.account.exception;

import lombok.Getter;
import org.slam.account.domain.Email;

@Getter
public class UsernameDuplicationException extends RuntimeException {
    private Email email;
    public UsernameDuplicationException(Email email) {
        this.email = email;
    }
}
