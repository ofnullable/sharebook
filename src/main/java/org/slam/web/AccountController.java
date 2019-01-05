package org.slam.web;

import org.slam.dto.account.Account;
import org.slam.service.account.AccountSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
	
	@Autowired
	private AccountSaveService accountSaveService;
	
	@GetMapping("/sign-in")
	public String signInPage(HttpServletRequest req, Authentication auth) {
		return this.setPrevPage(req, auth, "http://localhost:8080/sign-up", "sign-in");
	}
	
	@GetMapping("/sign-up")
	public String signUpPage(HttpServletRequest req, Authentication auth) {
		return this.setPrevPage(req, auth, "http://localhost:8080/sign-in", "sign-up");
	}
	
	@PostMapping("/sign-up")
	public String createAccount(Account account, RedirectAttributes rttr) {
		accountSaveService.save(account);
		rttr.addFlashAttribute("createResult", "success");
		return "redirect:/sign-in";
	}
	
	private String setPrevPage(HttpServletRequest req, Authentication auth, String exceptUrl, String toGo) {
		String prev = req.getHeader("Referer");
		System.out.println("PREV : " + prev + " TOGO : " + toGo);
		if ( auth != null ) {
			return "redirect:" + prev;
		}
		if ( !exceptUrl.equals(prev) && req.getSession().getAttribute("prev") == null ) {
			req.getSession().setAttribute("prev", prev);
		}
		return toGo;
	}
	
}
