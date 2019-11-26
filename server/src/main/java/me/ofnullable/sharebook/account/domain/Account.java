package me.ofnullable.sharebook.account.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.common.domain.Auditable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String avatar;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    @Transient
    private boolean verified;

    @Builder
    public Account(Email email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void addRole(RoleName roleName) {
        this.roles.add(buildRole(roleName));
    }

    public Account verified() {
        this.verified = true;
        return this;
    }

    public Account updateAvatar(String avatar) {
        this.avatar = avatar;
        return this.verified();
    }

    public Account update(UpdateAccountRequest dto, PasswordEncoder passwordEncoder) {
        if (StringUtils.hasText(dto.getName())) {
            this.name = dto.getName();
        }
        if (StringUtils.hasText(dto.getNewPassword())) {
            this.password = passwordEncoder.encode(dto.getNewPassword());
        }
        return this.verified();
    }

    private Role buildRole(RoleName roleName) {
        return Role.builder()
                .name(roleName)
                .account(this)
                .build();
    }

}
