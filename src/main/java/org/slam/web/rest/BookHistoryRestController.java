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

    /*
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
    */

}
