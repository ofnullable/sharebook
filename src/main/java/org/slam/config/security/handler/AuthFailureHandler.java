package org.slam.config.security.handler;

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

import static org.slam.config.security.handler.HandlerUtils.errorToString;

public class AuthFailureHandler implements AuthenticationFailureHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        log.debug("authentication failure: {}", e.getMessage());

        final var stringifiedError = errorToString(ErrorCode.BAD_CREDENTIALS, req.getRequestURI());

        res.setStatus(res.SC_BAD_REQUEST);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(stringifiedError);
        res.flushBuffer();
    }

}
