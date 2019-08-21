package org.slam.config.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
        /*
         * Make flash attribute for sign-in failure
         * @see <a href="https://stackoverflow.com/questions/23844546/flash-attribute-in-custom-authenticationfailurehandler">reference</a>
         */
        if (ex != null) {
            var flashMap = new FlashMap();
            flashMap.put("AUTHENTICATION_EXCEPTION", ex.getMessage());

            var flashMapManager = new SessionFlashMapManager();
            flashMapManager.saveOutputFlashMap(flashMap, req, res);
        }
        setDefaultFailureUrl("/sign-in?error");
        super.onAuthenticationFailure(req, res, ex);
    }

}
