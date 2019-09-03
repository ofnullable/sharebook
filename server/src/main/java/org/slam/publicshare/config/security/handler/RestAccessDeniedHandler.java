package org.slam.publicshare.config.security.handler;

import org.slam.publicshare.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slam.publicshare.config.security.handler.HandlerUtils.errorToString;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        log.debug("access denied, {}", e.getMessage());

        final var error = errorToString(ErrorCode.ACCESS_DENIED, req.getRequestURI());

        res.setStatus(res.SC_FORBIDDEN);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(error);
        res.flushBuffer();
    }

}
