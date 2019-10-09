package org.slam.publicshare.config.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slam.publicshare.config.security.handler.HandlerUtils.authToString;

public class RestAuthSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
        log.debug("authentication success: {}", authentication.getName());
        clearSessionAttribute(req); // clear authentication fail attribute

        final var auth = authToString(authentication);

        res.setStatus(res.SC_OK);
        res.setHeader("Set-Cookie","HttpOnly; Same-Site=None");
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(auth);
        res.flushBuffer();
    }

    private void clearSessionAttribute(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}