package org.slam.publicshare.config.security.userdetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {

    private String username;
    private String password;

    @Builder
    public SignInRequest(String username, String password) {
        this.username = username != null ? username.trim().toLowerCase() : null;
        this.password = password;
    }

}
