package org.slam.publicshare.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiError {

    private int status;
    private String message;
    private String path;
    private Map<String, String> errors;

    @Builder
    public ApiError(int status, String message, String path, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = initErrors(errors);
    }

    private Map<String, String> initErrors(Map<String, String> errors) {
        return errors == null ? new HashMap<>() : errors;
    }

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class FieldError {
//        private String field;
//        private Object value;
//        private String reason;
//
//        @Builder
//        public FieldError(String field, Object value, String reason) {
//            this.field = field;
//            this.value = value;
//            this.reason = reason;
//        }
//    }

}
