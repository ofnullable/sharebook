package org.slam.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slam.account.domain.Account;
import org.slam.account.domain.Role;

import java.util.Set;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
