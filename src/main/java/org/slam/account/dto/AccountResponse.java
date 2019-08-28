package org.slam.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slam.account.domain.Account;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResponse {

    private Long id;
    private String email;
    private String name;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.email = account.getEmail().getAddress();
        this.name = account.getName();
    }

}
