package org.slam.web;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.dto.book.Book;
import org.slam.service.book.BookSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/my-page/item")
@AllArgsConstructor
public class MyItemController {

    private final BookSaveService bookSaveService;

    @GetMapping("/")
    public String registerItem() {
        return "my-page/register";
    }

    @PostMapping("/")
    public String registerItem(Book book) {
        log.info("RECEIVED DATA : {}, CREATOR : {}", book, book.getCreatedBy());
        bookSaveService.save(book);
        return "redirect:/my-page/";
    }

    // TODO: Update, delete method

}