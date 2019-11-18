package me.ofnullable.sharebook.account.dto;

import lombok.Getter;
import me.ofnullable.sharebook.account.domain.Account;

@Getter
public class AccountResponse {

    private Long id;
    private String email;
    private String name;
    private boolean verified;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.email = account.getEmail().getAddress();
        this.name = account.getName();
        this.verified = account.isVerified();
    }

}
