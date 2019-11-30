package me.ofnullable.sharebook.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleAccountInfo {

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String avatar;

    private SimpleAccountInfo(Long id, String email, String name, String avatar) {
        this.accountId = id;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    public static SimpleAccountInfo of(Account account) {
        return new SimpleAccountInfo(account.getId(), account.getEmail().getAddress(), account.getName(), account.getAvatar());
    }

}
