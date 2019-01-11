package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.service.book.BookSelectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private final BookSelectService bookSelectService;

    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("books", bookSelectService.selectBookList());
        return "index";
    }

}