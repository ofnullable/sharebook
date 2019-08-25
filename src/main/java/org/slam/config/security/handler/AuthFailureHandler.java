package org.slam.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slam.error.ApiError;
import org.slam.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper mapper = new ObjectMapper();
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        log.debug("authentication failure: {}", e.getMessage());

        final var errorCode = ErrorCode.BAD_CREDENTIALS;
        final var error = ApiError.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .path(req.getRequestURI())
                .message(errorCode.getMessage())
                .build();

        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(mapper.writeValueAsString(error));
        res.flushBuffer();
    }

}
