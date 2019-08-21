package org.slam.account.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.slam.account.domain.Role;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    @Builder
    public Account(Email email, String password, String name) {
        this.email = email;
        this.password = encodePassword(password);
        this.name = name;
    }

    public void addRole(RoleName roleName) {
        this.roles.add(buildRole(roleName));
    }

    public void updatePassword(String password) {
        this.password = encodePassword(password);
    }

    private Role buildRole(RoleName roleName) {
        return Role.builder()
                .name(roleName)
                .account(this)
                .build();
    }

    private String encodePassword(String password) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
    }
}
