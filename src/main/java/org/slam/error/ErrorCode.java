package org.slam.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Field순서 변경에 유의
public enum ErrorCode {

    BAD_CREDENTIALS(400, "BCD", "Username and/or Password did not match"),

    ACCOUNT_NOT_FOUND(404, "ANF", "No such account"),
    EMAIL_DUPLICATION(409, "EDC", "Email duplication"),

    INVALID_INPUT_VALUE(400, "IIV", "Invalid value"),
    KEY_DUPLICATION(409, "KDC", "Key duplication");

    private final int status;
    private final String code;
    private final String message;
}
