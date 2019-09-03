package org.slam.publicshare.config.security.handler;

import org.slam.publicshare.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slam.publicshare.config.security.handler.HandlerUtils.errorToString;

public class RestUnauthorizedHandler implements AuthenticationEntryPoint {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        log.debug("unauthorized entry: {}", e.getMessage());

        final var error = errorToString(ErrorCode.UNAUTHORIZED, req.getRequestURI());

        res.setStatus(res.SC_UNAUTHORIZED);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(error);
        res.flushBuffer();
    }

}
