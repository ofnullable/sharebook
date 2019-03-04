package org.slam.handler;

import org.slam.dto.account.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        setAuthInfoToSession(req, auth);
        String prev = getPrevPage(req);
        clearPrevAttr(req);
        res.sendRedirect(Optional.ofNullable(prev).orElse("/"));
    }

    private void setAuthInfoToSession(HttpServletRequest req, Authentication auth) {
        req.getSession().setAttribute("auth", ((AccountDetails) auth.getPrincipal()).getAccount());
    }

    private String getPrevPage(HttpServletRequest req) {
        var requestedURL = req.getRequestURL().toString();
        if (requestedURL.endsWith("/sign-in")) {
            return (String) req.getSession().getAttribute("prev");
        } else {
            return requestedURL;
        }
    }

    private void clearPrevAttr(HttpServletRequest req) {
        req.getSession().removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        req.getSession().removeAttribute("prev");
    }

}