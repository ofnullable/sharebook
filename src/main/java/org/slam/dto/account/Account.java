package org.slam.dto.account;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class Account {

    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Set<Role> roles;

}