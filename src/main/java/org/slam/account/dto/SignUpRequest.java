package org.slam.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    private Email email;
    private String password;
    private String name;

    @Builder
    public SignUpRequest(Email email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

}
