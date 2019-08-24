package org.slam.error;

import org.slam.account.exception.AccountNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleAccountNotFoundException(AccountNotFoundException e) {
        if (e.getId() != null) {
            log.debug("No Such Account. ID: {}", e.getId());
        }
        if (e.getUsername() != null) {
            log.debug("No Such Account. Username: {}", e.getUsername());
        }
        return bindError(ErrorCode.ACCOUNT_NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("handle MethodArgumentNotValidException: {}", ex.getBindingResult());
        final var fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildResponseEntity(bindErrorWithFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrors));
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

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
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
