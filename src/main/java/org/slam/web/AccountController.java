package org.slam.web;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.dto.account.Account;
import org.slam.service.account.AccountSaveService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@AllArgsConstructor
public class AccountController {
	
	private final AccountSaveService accountSaveService;
	
	@GetMapping("/sign-in")
	public String signInPage(HttpServletRequest req, Authentication auth) {
		return this.setPrevPage(req, auth,  "sign-in");
	}
	
	@GetMapping("/sign-up")
	public String signUpPage(HttpServletRequest req, Authentication auth) {
		return this.setPrevPage(req, auth, "sign-up");
	}
	
	@PostMapping("/sign-up")
	public String createAccount(Account account, RedirectAttributes rttr) {
		accountSaveService.save(account);
		rttr.addFlashAttribute("createResult", "success");
		return "redirect:/sign-in";
	}
	
	private String setPrevPage(HttpServletRequest req, Authentication auth, String toGo) {
		String prev = req.getHeader("Referer");
		log.info("AUTHENTICATION : {}, PREV : {}, TOGO : {}", auth, prev, toGo);
		if ( auth != null ) {
			return prev != null ? "redirect:" + prev : "redirect:/";
		}
		if ( !"http://localhost:8080/sign-in".equals(prev) && !"http://localhost:8080/sign-up".equals(prev) && req.getSession().getAttribute("prev") == null ) {
			req.getSession().setAttribute("prev", prev);
		}
		return toGo;
	}
	
}
