package me.ofnullable.sharebook.account.utils;

import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;

public class AccountUtils {

    public static Account buildNormalAccount() {
        return Account.builder()
                .email(Email.of("test1@asd.com"))
                .name("test user1")
                .password("{noop}test")
                .build();
    }

    public static SignUpRequest buildNormalSignUpRequest(String email) {
        return SignUpRequest.builder()
                .email(Email.of(email))
                .name("test account")
                .password("{noop}test")
                .build();
    }

    public static SignUpRequest buildInvalidSignUpRequest() {
        return SignUpRequest.builder()
                .email(Email.of("invalid.com"))
                .name("test account")
                .password("{noop}test")
                .build();
    }

    public static Account buildAccountWithId() throws NoSuchFieldException, IllegalAccessException {
        var account = buildNormalAccount();
        var idField = account.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(account, 1L);
        return account;
    }

    public static UpdateAccountRequest buildUpdateDto(String name, String newPassword) {
        return UpdateAccountRequest.builder()
                .id(1L)
                .name(name)
                .newPassword(newPassword)
                .build();
    }

    public static UpdateAccountRequest buildUpdateDtoWithId(Long id, String name, String newPassword) {
        return UpdateAccountRequest.builder()
                .id(id)
                .name(name)
                .newPassword(newPassword)
                .build();
    }

}
