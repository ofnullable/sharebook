package me.ofnullable.sharebook.config.security.handler;

import me.ofnullable.sharebook.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthFailureHandler implements AuthenticationFailureHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException {
        log.debug("authentication failure: {}", e.getMessage());

        final var error = HandlerUtils.errorToString(ErrorCode.BAD_CREDENTIALS, req.getRequestURI());

        res.setStatus(res.SC_BAD_REQUEST);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(error);
        res.flushBuffer();
    }

}
