package me.ofnullable.sharebook.account.domain;

import me.ofnullable.sharebook.account.utils.AccountUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private Account account = Account.builder()
            .email(Email.of("asd@asd.com"))
            .name("test")
            .password("test")
            .build();

    @Test
    @DisplayName("Email객체에서 Id, Host 추출")
    void email_method() {
        var email = Email.of("test@test.com");

        assertEquals(email.getId(), "test");
        assertEquals(email.getHost(), "test.com");
    }

    @Test
    @DisplayName("Builder로 인스턴스 생성")
    void account_builder() {
        assertEquals(account.getEmail().getAddress(), "asd@asd.com");
        assertEquals(account.getName(), "test");
        assertEquals(account.getPassword(), "test");
    }

    @Test
    @DisplayName("Account의 편의 메서드를 활용하여 Role 추가")
    void add_role() {
        account.addRole(RoleName.BASIC);
        assertEquals(1, account.getRoles().size());
    }

    @Test
    @DisplayName("계정정보(비밀번호) 업데이트")
    void update_password() {
        var updateDto = AccountUtils.buildUpdateDto(null, "test1");
        account.update(updateDto, passwordEncoder);

        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

    @Test
    @DisplayName("계정정보(이름) 업데이트")
    void update_name() {
        var updateDto = AccountUtils.buildUpdateDto("테스트유저", null);
        account.update(updateDto, passwordEncoder);

        assertEquals(account.getName(), "테스트유저");
    }

}
