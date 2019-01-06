package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.account.AccountDetails;
import org.slam.service.book.BookSelectService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {
	
	private final BookSelectService bookSelectService;
	
	@GetMapping("/")
	public String selectMyBooks(@AuthenticationPrincipal AccountDetails auth, Model model) {
		model.addAttribute("books", bookSelectService.selectBookList(auth.getUsername()));
		return "my-page/index";
	}
	
}