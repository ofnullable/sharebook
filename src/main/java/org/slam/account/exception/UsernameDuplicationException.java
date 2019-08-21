package org.slam.account.exception;

import lombok.Getter;
import org.slam.account.domain.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email Duplication")
public class UsernameDuplicationException extends RuntimeException {
    private Email email;
    public UsernameDuplicationException(Email email) {
        this.email = email;
    }
}
