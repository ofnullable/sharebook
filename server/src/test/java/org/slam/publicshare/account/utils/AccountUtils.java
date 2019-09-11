package org.slam.publicshare.account.utils;

import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.domain.Email;
import org.slam.publicshare.account.dto.SignUpRequest;

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
