package org.slam.account.dto;

import lombok.Getter;
import org.slam.account.domain.Account;
import org.slam.account.domain.Role;

import java.util.Set;

@Getter
public class AccountResponse {

    private String email;
    private String name;
    private Set<Role> roles;

    public AccountResponse(Account account) {
        this.email = account.getEmail().getAddress();
        this.name = account.getName();
        this.roles = account.getRoles();
    }

}
