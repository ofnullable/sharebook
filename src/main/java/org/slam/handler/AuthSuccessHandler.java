package org.slam.handler;

import org.slam.dto.account.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
		setAuthInfoToSession(req, auth);
		String prev = getPrevPage(req);
		System.out.println(prev);
		clearPrevAttr(req);
		res.sendRedirect( Optional.ofNullable(prev).orElse("/") );
	}
	
	private void setAuthInfoToSession(HttpServletRequest req, Authentication auth) {
		req.getSession().setAttribute("auth", ((AccountDetails) auth.getPrincipal()).getAccount() );
	}
	
	private String getPrevPage(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("prev");
	}
	
	private void clearPrevAttr(HttpServletRequest req) {
		req.getSession().removeAttribute("prev");
	}
	
}
