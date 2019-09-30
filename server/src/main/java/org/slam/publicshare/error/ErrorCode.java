package org.slam.publicshare.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Field순서 변경에 유의
public enum ErrorCode {

    ACCESS_DENIED(403, "You don't have permission to view this page."),
    BAD_CREDENTIALS(400, "Username and/or Password did not match"),
    UNAUTHORIZED(401, "Unauthorized"),

    ACCOUNT_NOT_FOUND(404, "No such account"),
    EMAIL_DUPLICATION(409, "Email duplication"),

    BOOK_NOT_FOUND(404, "No such book"),
    INVALID_BOOK_STATUS(400, "No such book status"),

    CATEGORY_NOT_FOUND(404, "No such category"),

    RENTAL_NOT_FOUND(404, "No such rental"),
    RENTAL_STATUS_NOT_FOUND(404, "No such rental status"),
    RENTAL_ALREADY_COMPLETION(400, "Rental already completion"),
    RENTAL_NOT_REQUESTED(400, "Rental did not requested"),
    RENTAL_STATUS_EQUALS(400, "Can not change with same status"),
    INVALID_RENTAL_STATUS(400, "Can not change invalid status"),

    INVALID_INPUT_VALUE(400, "Invalid value"),
    KEY_DUPLICATION(409, "Key duplication");

    private final int status;
    private final String message;

}
