package org.slam.account;

import org.junit.jupiter.api.Test;
import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.slam.account.domain.RoleName;
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
    public void account_builder_test() {
        logger.info("email: {}, name: {}, password: {}", account.getEmail().getAddress(), account.getName(), account.getPassword());
        assertEquals(account.getEmail().getAddress(), "asd@asd.com");
        assertEquals(account.getName(), "test");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

    @Test
    public void add_role_test() {
        account.addRole(RoleName.BASIC);
        logger.info("roles: {}", account.getRoles());
        assertEquals(1, account.getRoles().size());
    }

    @Test
    public void update_password_test() {
        account.updatePassword("modifyTest");
        assertTrue(account.getPassword().startsWith("{bcrypt}"));
    }

}
