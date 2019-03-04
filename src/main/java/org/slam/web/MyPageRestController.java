package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.common.Paginator;
import org.slam.service.book.BookSelectService;
import org.slam.service.book.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/my-page")
public class MyPageRestController {

    private final BookSelectService bookSelectService;
    private final HistoryService historyService;

    @GetMapping("/{status}")
    public Map<String, Object> findMyItems(@PathVariable String status, Authentication auth, @ModelAttribute("paginator") Paginator paginator) {
        paginator.setUsername(auth.getName());
        switch (status) {
            case "my-books" :
                return bookSelectService.findBookListByOwner(paginator);
            case "on-loan" :
                return historyService.findMatchStatusHistory(BookStatus.ON_LOAN, paginator);
            case "on-apply" :
                return historyService.findMatchStatusHistory(BookStatus.WAIT_FOR_RESPONSE, paginator);
            case "on-resv" :
                return historyService.findMatchStatusHistory(BookStatus.ON_RESERVED, paginator);
            default:
                return null;
        }
    }

    @GetMapping("/{id}/history")
    public List<BookHistory> findBookRequestHistoryById(@PathVariable Long id, Authentication auth) {
        return historyService.findBookRequestHistoryById(id, auth.getName());
    }

    @PostMapping("/{id}/history")
    public String updateBookHistory(Book book, Authentication auth) {
        historyService.updateBookHistory(book, auth.getName());
        return "success";
    }

}
