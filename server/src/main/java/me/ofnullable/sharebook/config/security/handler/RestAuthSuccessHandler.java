package me.ofnullable.sharebook.config.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.ofnullable.sharebook.config.security.handler.HandlerUtils.authToString;

public class RestAuthSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
        log.debug("authentication success: {}", authentication.getName());

        var auth = authToString(authentication);

        res.setStatus(res.SC_OK);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(auth);
        res.flushBuffer();
    }

}