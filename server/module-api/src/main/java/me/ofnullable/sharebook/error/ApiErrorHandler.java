package me.ofnullable.sharebook.error;

import me.ofnullable.sharebook.account.exception.EmailDuplicationException;
import me.ofnullable.sharebook.account.exception.PasswordNotMatchingException;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.lending.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleIllegalArgumentException(RuntimeException e, WebRequest request) {
        log.debug("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        return bindError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.debug("Fail to find resource, key: {}", e.getKey());
        return bindError(HttpStatus.NOT_FOUND.value(), e.getMessage(), request);
    }

    @ExceptionHandler(EmailDuplicationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ApiError handleEmailDuplicationException(EmailDuplicationException e, WebRequest request) {
        log.debug("Duplicate email: {}", e.getEmail());
        return bindError(ErrorCode.EMAIL_DUPLICATION, request);
    }

    @ExceptionHandler(PasswordNotMatchingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handlePasswordNotMatchingException(PasswordNotMatchingException e, WebRequest request) {
        log.debug("Password did not match.");
        return bindError(ErrorCode.PASSWORD_DID_NOT_MATCH, request);
    }

    @ExceptionHandler(LendingAlreadyCompletionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleLendingAlreadyCompletionException(LendingAlreadyCompletionException e, WebRequest request) {
        log.debug("Lending Already Completion.");
        return bindError(ErrorCode.LENDING_ALREADY_COMPLETION, request);
    }

    @ExceptionHandler(LendingNotRequestedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleLendingNotRequestedException(LendingNotRequestedException e, WebRequest request) {
        log.debug("This Lending Is Not Requested. id: {}", e.getLendingId());
        return bindError(ErrorCode.LENDING_NOT_REQUESTED, request);
    }

    @ExceptionHandler(LendingStatusEqualsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleLendingStatusEqualsException(LendingStatusEqualsException e, WebRequest request) {
        log.debug("Lending Status Can Not Equals. status: {}", e.getStatus());
        return bindError(ErrorCode.LENDING_STATUS_EQUALS, request);
    }

    @ExceptionHandler(LendingStatusInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleLendingStatusInvalidException(LendingStatusInvalidException e, WebRequest request) {
        log.debug("Lending Status Invalid. status: {}", e.getStatus());
        return bindErrorWithFields(ErrorCode.INVALID_LENDING_STATUS, e.getStatus(), request);
    }

    @ExceptionHandler(LendingHistoryNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleLendingHistoryNotExistsException(LendingHistoryNotExistsException e, WebRequest request) {
        log.debug("Lending history not exists. book id: {}", e.getBookId());
        return bindError(ErrorCode.LENDING_HISTORY_NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("handle BindException: {}", ex.getBindingResult());
        final var fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildResponseEntity(bindErrorWithFields(ErrorCode.INVALID_INPUT_VALUE, fieldErrors, request));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("handle BindException: {}", ex.getBindingResult());
        final var fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildResponseEntity(bindErrorWithFields(ErrorCode.INVALID_INPUT_VALUE, fieldErrors, request));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    private Map<String, String> getFieldErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    private ApiError bindError(int status, String message, WebRequest request) {
        var req = ((ServletWebRequest) request).getRequest();
        return ApiError.builder()
                .status(status)
                .message(message)
                .path(req.getRequestURI())
                .build();
    }

    private ApiError bindError(ErrorCode code, WebRequest request) {
        var req = ((ServletWebRequest) request).getRequest();
        return ApiError.builder()
                .status(code.getStatus())
                .message(code.getMessage())
                .path(req.getRequestURI())
                .build();
    }

    private ApiError bindErrorWithFields(ErrorCode code, Map<String, String> errors, WebRequest request) {
        var req = ((ServletWebRequest) request).getRequest();
        return ApiError.builder()
                .status(code.getStatus())
                .message(code.getMessage())
                .path(req.getRequestURI())
                .errors(errors)
                .build();
    }

}