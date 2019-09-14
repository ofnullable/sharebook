package org.slam.publicshare.account.dto;

import lombok.Getter;
import lombok.ToString;
import org.slam.publicshare.account.domain.Account;

@Getter @ToString
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
