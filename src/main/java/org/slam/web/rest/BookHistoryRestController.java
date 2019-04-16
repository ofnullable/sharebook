package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.service.bookhistory.BookHistoryService;
import org.slam.service.history.HistoryUpdateService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookHistoryRestController {

    private final BookHistoryService bookHistoryService;
    private final HistoryUpdateService historyUpdateService;

    @PostMapping("/loan/{id}/{username}")
    public int loanRequest(@PathVariable Long id, @PathVariable String username) {
        return bookHistoryService.loanRequest(id, username);
    }

    @PostMapping("/reservation/{id}/{username}")
    public int reservationRequest(@PathVariable Long id, @PathVariable String username) {
        return historyUpdateService.reservationRequest(id, username);
    }

    @PostMapping("/cancel-reservation/{id}/{username}")
    public int cancelReservationRequest(@PathVariable Long id, @PathVariable String username) {
        return historyUpdateService.cancelReservationRequest(id, username);
    }

    @PostMapping("/cancel/{id}/{username}")
    public int cancelRequest(@PathVariable Long id, @PathVariable String username) {
        return bookHistoryService.cancelLoanRequest(id, username);
    }

    @PostMapping("/return/{id}/{username}")
    public int returnRequest(@PathVariable Long id, @PathVariable String username) {
        return historyUpdateService.returnRequest(id, username);
    }

    @PostMapping("/{id}")
    public int saveRequest(Book book) {
        System.out.println(book);
        return 0;
    }

    @PatchMapping("/{id}")
    public int updateRequest(Book book) {
        System.out.println(book);
        return 0;
    }

}
