package me.ofnullable.sharebook.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateAccountRequest {

    @NotNull
    private Long id;
    private String name;
    private String newPassword;

    @Builder
    public UpdateAccountRequest(Long id, String name, String newPassword) {
        if (!StringUtils.hasText(name) && !StringUtils.hasText(newPassword)) {
            throw new IllegalArgumentException("수정하려는 데이터를 입력해주세요.");
        }
        this.id = id;
        this.name = StringUtils.hasText(name) ? name : null;
        this.newPassword = StringUtils.hasText(newPassword) ? newPassword : null;
    }

}
