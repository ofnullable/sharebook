package me.ofnullable.sharebook.account.utils;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.SignUpRequest;

public class AccountUtils {

    public static Account buildNormalAccount() {
        return Account.builder()
                .email(Email.of("test1@asd.com"))
                .name("test user1")
                .password("{noop}test")
                .build();
    }

    public static SignUpRequest buildNormalSignUpRequest(String email) {
        return SignUpRequest.builder()
                .email(Email.of(email))
                .name("test account")
                .password("{noop}test")
                .build();
    }

    public static SignUpRequest buildInvalidSignUpRequest() {
        return SignUpRequest.builder()
                .email(Email.of("invalid.com"))
                .name("test account")
                .password("{noop}test")
                .build();
    }

}
