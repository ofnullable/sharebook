package me.ofnullable.sharebook.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

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
    @DisplayName("비밀번호 업데이트")
    void update_password() {
        account.updatePassword("modifyTest");
        assertEquals(account.getPassword(), "modifyTest");
    }

}
