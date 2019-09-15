package org.slam.publicshare.error;

import org.slam.publicshare.account.exception.EmailDuplicationException;
import org.slam.publicshare.account.exception.NoSuchAccountException;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.exception.NoSuchCategoryException;
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

    @ExceptionHandler(NoSuchAccountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleNoSuchAccountException(NoSuchAccountException e, WebRequest request) {
        if (e.getId() != null) {
            log.debug("No Such Account. ID: {}", e.getId());
        } else {
            log.debug("No Such Account. Username: {}", e.getUsername());
        }
        return bindError(ErrorCode.ACCOUNT_NOT_FOUND, request);
    }

    @ExceptionHandler(EmailDuplicationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ApiError handleEmailDuplicationException(EmailDuplicationException e, WebRequest request) {
        log.debug("Duplicate email: {}", e.getEmail());
        return bindError(ErrorCode.EMAIL_DUPLICATION, request);
    }

    @ExceptionHandler(NoSuchBookException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleNoSuchBookException(NoSuchBookException e, WebRequest request) {
        log.debug("No Such Book. id: {}", e.getId());
        return bindError(ErrorCode.BOOK_NOT_FOUND, request);
    }

    @ExceptionHandler(NoSuchCategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleNoSuchCategoryException(NoSuchCategoryException e, WebRequest request) {
        log.debug("No Such Category. name: {}", e.getName());
        return bindError(ErrorCode.CATEGORY_NOT_FOUND, request);
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

    private Map<String, String> getFieldErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    private ApiError bindError(ErrorCode errorCode, WebRequest request) {
        var req = ((ServletWebRequest) request).getRequest();
        return ApiError.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .path(req.getRequestURI())
                .build();
    }

    private ApiError bindErrorWithFields(ErrorCode errorCode, Map<String, String> errors, WebRequest request) {
        var req = ((ServletWebRequest) request).getRequest();
        return ApiError.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .path(req.getRequestURI())
                .errors(errors)
                .build();
    }

}
