package org.slam.web;

import org.slam.service.BookSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookSelectService bookSelectService;
	
	@GetMapping("/{id}")
	public String getBookDetail(@PathVariable Long id, Model model) {
		var book = bookSelectService.selectBookDetail(id);
		System.out.println(book);
		model.addAttribute("book", book);
		return "book/detail";
	}
	
}
