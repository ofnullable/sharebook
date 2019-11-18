package me.ofnullable.sharebook.account.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class UpdateAccountRequestTest {

    @Test
    @DisplayName("UpdateAccountRequest의 이름과 비밀번호가 모두 null인경우 - IllegalArgumentException")
    void update_request_dto() {
        var exception = catchThrowable(() -> UpdateAccountRequest.builder().build());
        then(exception)
                .isInstanceOf(IllegalArgumentException.class);
    }

}
