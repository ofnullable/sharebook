package org.slam.publicshare.config.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slam.publicshare.account.dto.AccountResponse;
import org.slam.publicshare.config.security.userdetails.AccountDetails;
import org.slam.publicshare.error.ApiError;
import org.slam.publicshare.error.ErrorCode;
import org.springframework.security.core.Authentication;

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
