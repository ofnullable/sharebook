package org.slam.testutil;

import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.dto.SignUpRequest;

public class AccountUtils {

    public static Account buildNormalAccount() {
        return Account.builder()
                .email(Email.of("test1@asd.com"))
                .password("test")
                .name("test user1")
                .build();
    }

    public static SignUpRequest buildNormalSignUpRequest(String email) {
        return SignUpRequest.builder()
                .email(Email.of(email))
                .name("test account")
                .password("test")
                .build();
    }

    public static SignUpRequest buildInvalidSignUpRequest() {
        return SignUpRequest.builder()
                .email(Email.of("invalid.com"))
                .name("test account")
                .password("test")
                .build();
    }

}
