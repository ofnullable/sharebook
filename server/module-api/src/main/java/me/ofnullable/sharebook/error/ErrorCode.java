package me.ofnullable.sharebook.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Field 순서 변경에 유의
public enum ErrorCode {

    // common
    RESOURCE_NOT_FOUND(404, "요청한 데이터가 존재하지 않습니다."),
    INVALID_INPUT_VALUE(400, "올바른 형식의 데이터를 입력해 주세요."),

    // authentication/account
    BAD_CREDENTIALS(400, "아이디 또는 비밀번호를 다시 확인해 주세요."),
    UNAUTHORIZED(401, "로그인 후 이용할 수 있습니다."),
    ACCESS_DENIED(403, "조회할 수 있는 권한이 없습니다."),
    PASSWORD_DID_NOT_MATCH(400, "비밀번호가 일치하지 않습니다."),
    EMAIL_DUPLICATION(409, "이미 사용중인 이메일 입니다."),

    // book
    INVALID_BOOK_STATUS(400, "올바르지 않은 도서 상태 입니다."),

    // lending
    LENDING_HISTORY_NOT_FOUND(404, "대여기록이 존재하지 않습니다."),
    LENDING_ALREADY_COMPLETION(400, "이미 종료되었습니다."),
    LENDING_NOT_REQUESTED(400, "요청한 기록이 존재하지 않습니다."),
    LENDING_STATUS_EQUALS(400, "동일한 대여 상태로 업데이트할 수 없습니다."),
    INVALID_LENDING_STATUS(400, "올바르지 않은 대여 상태 입니다.");

    private final int status;
    private final String message;

}
