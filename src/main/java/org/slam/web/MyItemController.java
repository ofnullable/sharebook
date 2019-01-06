package org.slam.web;

import lombok.extern.log4j.Log4j2;
import org.slam.dto.book.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/my-page/item")
public class MyItemController {
	
	@GetMapping("/register")
	public String registerItem() {
		return "/my-page/register";
	}
	
	@PostMapping("/register")
	public String registerItem(Book book) {
		log.info("RECEIVED DATA : {}", book);
		return "redirect:/my-page/";
	}
	
}
