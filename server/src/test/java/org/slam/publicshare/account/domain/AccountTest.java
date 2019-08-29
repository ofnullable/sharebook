package org.slam.publicshare.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Account account = Account.builder()
            .email(Email.of("asd@asd.com"))
            .name("test")
            .password("test")
            .build();

    @Test
    @DisplayName("Builder로 인스턴스 생성")
    public void account_builder_test() {
        logger.info("email: {}, name: {}, password: {}", account.getEmail().getAddress(), account.getName(), account.getPassword());
        assertEquals(account.getEmail().getAddress(), "asd@asd.com");
        assertEquals(account.getName(), "test");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

    @Test
    @DisplayName("Account의 편의 메서드를 활용하여 Role 추가")
    public void add_role_test() {
        account.addRole(RoleName.BASIC);
        logger.info("roles: {}", account.getRoles());
        assertEquals(1, account.getRoles().size());
    }

    @Test
    @DisplayName("비밀번호 업데이트")
    public void update_password_test() {
        account.updatePassword("modifyTest");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

}
