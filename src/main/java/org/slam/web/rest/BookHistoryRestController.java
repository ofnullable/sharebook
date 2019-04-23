package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.service.bookhistory.BookHistoryService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookHistoryRestController {

    private final BookHistoryService bookHistoryService;

    @PostMapping("/{id}")
    public int saveRequest(Book book) {
        return bookHistoryService.saveNewRequest(book);
    }

    @PatchMapping("/{id}")
    public int updateRequest(Book book) {
        return bookHistoryService.updateToMatchRequest(book);
    }

}
