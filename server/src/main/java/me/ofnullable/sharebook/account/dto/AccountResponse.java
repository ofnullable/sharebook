package me.ofnullable.sharebook.account.dto;

import lombok.Getter;
import lombok.ToString;
import me.ofnullable.sharebook.account.domain.Account;

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
