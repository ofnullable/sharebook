package org.slam.web;

import org.slam.service.BookSelectService;
import org.slam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@Autowired
	private BookSelectService bookSelectService;
	
	@GetMapping("/")
	public String sayHello(Model model) {
		model.addAttribute("books", bookSelectService.selectBookList());
		return "index";
	}
	
}
