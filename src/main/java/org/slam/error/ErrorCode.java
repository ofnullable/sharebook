package org.slam.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Field순서 변경에 유의
public enum ErrorCode {

    ACCOUNT_NOT_FOUND(404, "ANF", "No Such Account"),
    EMAIL_DUPLICATION(400, "EDC", "Email Duplication"),

    INVALID_INPUT_VALUE(400, "IIV", "Invalid Value"),
    KEY_DUPLICATION(400, "KDC", "Key Duplication (Database)");

    private final int status;
    private final String code;
    private final String message;
}
