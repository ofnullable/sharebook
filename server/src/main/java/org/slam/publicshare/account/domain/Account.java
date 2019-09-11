package org.slam.publicshare.account.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.common.domain.Auditable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

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

    public void addBook(Book book) {
        this.books.add(book);
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
