package org.slam.web.admin;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.service.account.AccountSelectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/user")
public class UserManagementController {

	private final AccountSelectService accountSelectService;
	
	@GetMapping("/list")
	public List<Account> selectAllUser() {
		return accountSelectService.findAll();
	}

}
