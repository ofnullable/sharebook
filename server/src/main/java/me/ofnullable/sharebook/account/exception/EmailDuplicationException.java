package me.ofnullable.sharebook.account.exception;

import lombok.Getter;
import me.ofnullable.sharebook.account.domain.Email;

@Getter
public class EmailDuplicationException extends RuntimeException {
    private Email email;
    public EmailDuplicationException(Email email) {
        this.email = email;
    }
}
