package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.service.book.BookSelectService;
import org.slam.service.book.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/my-page")
public class MyPageRestController {

    private final BookSelectService bookSelectService;
    private final HistoryService historyService;

    @GetMapping("/my-items")
    public List<Book> selectMyItems(Authentication auth) {
        return bookSelectService.selectBookListByOwner(auth.getName());
    }

    @GetMapping("/on-loan")
    public List<Book> selectMyLoanItems(Authentication auth) {
        return historyService.selectMatchStatusHistory(BookStatus.ON_LOAN, auth.getName());
    }

    @GetMapping("/on-apply")
    public List<Book> selectMyApplyItems(Authentication auth) {
        return historyService.selectMatchStatusHistory(BookStatus.WAIT_FOR_RESPONSE, auth.getName());
    }

    @GetMapping("/on-resv")
    public List<Book> selectMyReservationItems(Authentication auth) {
        return historyService.selectMatchStatusHistory(BookStatus.ON_RESERVED, auth.getName());
    }

}
