package org.slam.publicshare.config.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slam.publicshare.config.security.handler.HandlerUtils.authToString;

public class RestAuthSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
        log.debug("authentication success: {}", authentication.getName());

        var auth = authToString(authentication);
        var cookie = makeSameSiteCookie();

        res.setStatus(res.SC_OK);
        res.addCookie(cookie);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(auth);
        res.flushBuffer();
    }

    private Cookie makeSameSiteCookie() {
        var cookie = new Cookie("SameSite", "Lax");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        return cookie;
    }

}