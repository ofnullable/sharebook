package org.slam.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handelBindException(BindException e) {
        log.warn("handle BindException");
        final var fieldErrors = getFieldErrors(e.getBindingResult());
        return bindErrorWithFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        final var fieldErrors = getFieldErrors(e.getBindingResult());
        return bindErrorWithFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
    }

    private List<ApiError.FieldError> getFieldErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().parallelStream()
                .map(e -> ApiError.FieldError.builder()
                           .field(e.getField())
                           .reason(e.getDefaultMessage())
                           .value(e.getRejectedValue())
                           .build()
                ).collect(toList());
    }

    private ResponseEntity<Object> buildResponse(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private ApiError bindError(ErrorCode errorCode) {
        return ApiError.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    private ApiError bindErrorWithFieldErrors(ErrorCode errorCode, List<ApiError.FieldError> errors) {
        return ApiError.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }

}
