package org.slam.testutil;

import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.dto.SignUpRequest;

public class AccountUtils {

    public static Account buildNormalAccount() {
        return Account.builder()
                .email(Email.of("test@test.com"))
                .name("test account")
                .password("test")
                .build();
    }

    public static SignUpRequest buildNormalSignUpRequest() {
        return SignUpRequest.builder()
                .email(Email.of("test@test.com"))
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
