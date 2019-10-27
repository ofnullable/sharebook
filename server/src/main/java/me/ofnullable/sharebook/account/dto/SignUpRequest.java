package me.ofnullable.sharebook.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @Valid
    private Email email;
    @Size(min = 6, max = 20)
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
