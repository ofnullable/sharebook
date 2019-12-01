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
    private String name;

    private String avatar;

    private SimpleAccountInfo(Long id, String name, String avatar) {
        this.accountId = id;
        this.name = name;
        this.avatar = avatar;
    }

    public static SimpleAccountInfo of(Account account) {
        return new SimpleAccountInfo(account.getId(), account.getName(), account.getAvatar());
    }

}
