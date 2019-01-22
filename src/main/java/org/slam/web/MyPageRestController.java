package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.service.book.BookSelectService;
import org.slam.service.book.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/my-page/history")
public class MyPageRestController {

    private final BookSelectService bookSelectService;
    private final HistoryService historyService;

    @GetMapping("/{status}")
    public List<Book> selectMyItems(@PathVariable String status, Authentication auth) {
        switch (status) {
            case "my-books" :
                return bookSelectService.selectBookListByOwner(auth.getName());
            case "on-loan" :
                return historyService.selectMatchStatusHistory(BookStatus.ON_LOAN, auth.getName());
            case "on-apply" :
                return historyService.selectMatchStatusHistory(BookStatus.WAIT_FOR_RESPONSE, auth.getName());
            case "on-resv" :
                return historyService.selectMatchStatusHistory(BookStatus.ON_RESERVED, auth.getName());
            default:
                return null;
        }
    }

}
