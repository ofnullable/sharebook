package org.slam.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiError {

    private HttpStatus status;
    private String code;
    private String message;
    private List<FieldError> errors;

    @Builder
    public ApiError(int status, String code, String message, List<FieldError> errors) {
        this.status = HttpStatus.valueOf(status);
        this.code = code;
        this.message = message;
        this.errors = initErrors(errors);
    }

    private List<FieldError> initErrors(List<FieldError> errors) {
        return errors == null ? new ArrayList<>() : errors;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private Object value;
        private String reason;

        @Builder
        public FieldError(String field, Object value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}
