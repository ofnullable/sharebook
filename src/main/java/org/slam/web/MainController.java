package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.common.Paginator;
import org.slam.service.book.BookSelectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class MainController {

    private final BookSelectService bookSelectService;

    @GetMapping("/")
    public String sayHello(Model model, @ModelAttribute("paginator") Paginator paginator) {
        model.addAttribute("books", bookSelectService.selectBookList(paginator));
        return "index";
    }

}