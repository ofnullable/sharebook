package org.slam.publicshare.dto.account;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class AccountDto {

    private String        username;
    private String        password;
    private String        name;
    private String        email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Set<RoleDto> roleDtos;

}