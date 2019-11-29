package me.ofnullable.sharebook.config.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.dto.AccountResponse;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
import me.ofnullable.sharebook.error.ApiError;
import me.ofnullable.sharebook.error.ErrorCode;
import org.springframework.security.core.Authentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HandlerUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static String authToString(Authentication auth) throws JsonProcessingException {
        final var account = ((AccountDetails) auth.getPrincipal()).getAccount();
        return mapper.writeValueAsString(new AccountResponse(account));
    }

    static String errorToString(ErrorCode errorCode, String requestURI) throws JsonProcessingException {
        var apiError = ApiError.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .path(requestURI)
                .build();
        return mapper.writeValueAsString(apiError);
    }

}
