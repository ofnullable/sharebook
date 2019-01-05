package org.slam.web;

import org.slam.dto.account.Account;
import org.slam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/sign-in")
	public void signInPage() {
	
	}
	
	@GetMapping("/user/all")
	public @ResponseBody List<Account> findAll() {
		return accountService.findAll();
	}
	
}
