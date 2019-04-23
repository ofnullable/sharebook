package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.BookHistory;
import org.slam.service.history.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/history")
public class HistoryRestController {

    private final HistoryService historyService;

    @GetMapping("/{bookId}")
    public List<BookHistory> findHistoriesByBookId(@PathVariable Long bookId, Authentication auth) {
        if (auth != null) return historyService.findHistoryByBookId(bookId, auth.getName());
        else return null;
    }

}
