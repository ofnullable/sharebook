package org.slam.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slam.config.security.handler.HandlerUtils.authToString;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper mapper = new ObjectMapper();
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        log.debug("authentication success: {}", auth.getName());
        clearAuthFailAttribute(req); // clear authentication fail attribute

        final var stringifiedAuth = authToString(auth);

        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(stringifiedAuth);
        res.flushBuffer();
    }

    private void clearAuthFailAttribute(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}