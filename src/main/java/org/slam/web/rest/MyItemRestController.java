package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.common.Paginator;
import org.slam.service.book.BookSelectService;
import org.slam.service.history.HistorySelectService;
import org.slam.service.history.HistoryUpdateService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/my-item")
public class MyItemRestController {

    private final BookSelectService bookSelectService;
    private final HistorySelectService historySelectService;
    private final HistoryUpdateService historyUpdateService;

    @GetMapping("/{status}")
    public Map<String, Object> findMyItems(@PathVariable String status, Authentication auth, @ModelAttribute("paginator") Paginator paginator) {
        paginator.setUsername(auth.getName());
        switch (status) {
            case "my-books" :
                return bookSelectService.findBookListByOwner(paginator);
            case "on-loan" :
                return historySelectService.findMatchStatusHistory(BookStatus.ON_LOAN, paginator);
            case "on-apply" :
                return historySelectService.findMatchStatusHistory(BookStatus.WAIT_FOR_RESPONSE, paginator);
            case "on-resv" :
                return historySelectService.findMatchStatusHistory(BookStatus.ON_RESERVED, paginator);
            default:
                return null;
        }
    }

    @GetMapping("/{id}/history")
    public List<BookHistory> findBookRequestHistoryById(@PathVariable Long id, Authentication auth) {
        return historySelectService.findBookRequestHistoryById(id, auth.getName());
    }

    @PatchMapping("/{id}/history")
    public int updateItemHistory(Book book) {
        return historyUpdateService.updateToMatchStatus(book);
    }

}
