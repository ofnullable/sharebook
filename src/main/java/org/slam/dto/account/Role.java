package org.slam.dto.account;

import lombok.*;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class Role {

    private Long id;
    private String name;

}