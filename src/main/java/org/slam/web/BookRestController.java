package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.service.book.BookUpdateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookRestController {

    private final BookUpdateService bookUpdateService;

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

}
