package me.ofnullable.sharebook.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    private Account account = Account.builder()
            .email(Email.of("asd@asd.com"))
            .name("test")
            .password("test")
            .build();

    @Test
    @DisplayName("Email객체에서 Id, Host 추출")
    public void  email_method() {
        var email = Email.of("test@test.com");

        assertEquals(email.getId(), "test");
        assertEquals(email.getHost(), "test.com");
    }

    @Test
    @DisplayName("Builder로 인스턴스 생성")
    public void account_builder() {
        assertEquals(account.getEmail().getAddress(), "asd@asd.com");
        assertEquals(account.getName(), "test");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

    @Test
    @DisplayName("Account의 편의 메서드를 활용하여 Role 추가")
    public void add_role() {
        account.addRole(RoleName.BASIC);
        assertEquals(1, account.getRoles().size());
    }

    @Test
    @DisplayName("비밀번호 업데이트")
    public void update_password() {
        account.updatePassword("modifyTest");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

}