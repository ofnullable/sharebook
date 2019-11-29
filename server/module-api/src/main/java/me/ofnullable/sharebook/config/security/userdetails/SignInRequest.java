package me.ofnullable.sharebook.config.security.userdetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {

    private String username;
    private String password;

    @Builder
    public SignInRequest(String username, String password) {
        this.username = StringUtils.hasText(username) ? username.trim().toLowerCase() : null;
        this.password = password;
    }

}
