package org.slam.testutil;

import org.slam.account.domain.Account;
import org.slam.account.domain.Email;

public class AccountUtils {

    public static Account buildNormalAccount() {
        return Account.builder()
                .email(Email.of("test@test.com"))
                .name("test account")
                .password("test")
                .build();
    }

}
