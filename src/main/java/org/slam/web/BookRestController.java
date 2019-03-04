package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.book.BookHistory;
import org.slam.service.book.BookUpdateService;
import org.slam.service.book.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookRestController {

    private final BookUpdateService bookUpdateService;
    private final HistoryService historyService;

    @PostMapping("/loan/{id}/{username}")
    public int loanRequest(@PathVariable Long id, @PathVariable String username) {
        return bookUpdateService.loanRequest(id, username);
    }

    @PostMapping("/reservation/{id}/{username}")
    public int reservationRequest(@PathVariable Long id, @PathVariable String username) {
        return bookUpdateService.reservationRequest(id, username);
    }

    @PostMapping("/cancel-reservation/{id}/{username}")
    public int cancelReservationRequest(@PathVariable Long id, @PathVariable String username) {
        return bookUpdateService.cancelReservationRequest(id, username);
    }

    @PostMapping("/cancel/{id}/{username}")
    public int cancelRequest(@PathVariable Long id, @PathVariable String username) {
        return bookUpdateService.cancelLoanRequest(id, username);
    }

    @PostMapping("/return/{id}/{username}")
    public int returnRequest(@PathVariable Long id, @PathVariable String username) {
        return bookUpdateService.returnRequest(id, username);
    }

    @GetMapping("/{bookId}/history")
    public List<BookHistory> selectHistoryByBookId(@PathVariable Long bookId, Authentication auth) {
        if (auth != null) return historyService.findHistoryByBookId(bookId, auth.getName());
        else return historyService.findHistoryByBookId(bookId, null);
    }

}
