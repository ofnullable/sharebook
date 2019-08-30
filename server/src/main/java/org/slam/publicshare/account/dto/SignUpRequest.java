package org.slam.publicshare.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.domain.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @Valid
    private Email email;
    @NotBlank
    private String password;
    @NotBlank
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
