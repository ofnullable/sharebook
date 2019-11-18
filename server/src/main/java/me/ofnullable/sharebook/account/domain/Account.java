package me.ofnullable.sharebook.account.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.common.domain.Auditable;

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

    public void updatePassword(String password) {
        this.password = password;
    }

    private Role buildRole(RoleName roleName) {
        return Role.builder()
                .name(roleName)
                .account(this)
                .build();
    }

}
