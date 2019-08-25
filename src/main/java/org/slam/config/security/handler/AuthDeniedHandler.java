package org.slam.config.security.handler;

import org.slam.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slam.config.security.handler.HandlerUtils.errorToString;

public class AuthDeniedHandler implements AccessDeniedHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        log.debug("access denied, {}", e.getMessage());

        var stringifiedError = errorToString(ErrorCode.ACCESS_DENIED, req.getRequestURI());

        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(stringifiedError);
        res.flushBuffer();
    }

}
