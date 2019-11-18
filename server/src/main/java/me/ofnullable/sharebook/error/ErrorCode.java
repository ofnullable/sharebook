package me.ofnullable.sharebook.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Field 순서 변경에 유의
public enum ErrorCode {

    // common
    RESOURCE_NOT_FOUND(404, "Can not find resource"),
    INVALID_INPUT_VALUE(400, "Invalid value"),

    // authentication/account
    BAD_CREDENTIALS(400, "Username and/or Password did not match"),
    UNAUTHORIZED(401, "You must sign in for this resource"),
    ACCESS_DENIED(403, "You don't have permission to view this page."),
    PASSWORD_DID_NOT_MATCH(400, "Password did not match"),
    EMAIL_DUPLICATION(409, "Email duplication"),

    // book
    INVALID_BOOK_STATUS(400, "Invalid book status"),

    // lending
    LENDING_HISTORY_NOT_FOUND(404, "There is no lending history"),
    LENDING_ALREADY_COMPLETION(400, "Lending already completion"),
    LENDING_NOT_REQUESTED(400, "Lending did not requested"),
    LENDING_STATUS_EQUALS(400, "Can not change with same status"),
    INVALID_LENDING_STATUS(400, "Invalid lending status");

    private final int status;
    private final String message;

}
