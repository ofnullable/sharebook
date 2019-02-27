package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.common.Paginator;
import org.slam.service.book.BookSelectService;
import org.slam.service.book.CommentService;
import org.slam.service.book.HistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final CommentService commentService;
    private final HistoryService historyService;
    private final BookSelectService bookSelectService;

    @GetMapping("/{id}")
    public String findBookDetail(@PathVariable Long id, Model model, Authentication auth, Paginator paginator) {
        model.addAttribute("book", bookSelectService.findBookDetail(id, auth));
//        model.addAttribute("comments", commentService.findCommentsByBookId(id, paginator));
        return "book/detail";
    }

    @GetMapping("/{bookId}/histories")
    public String findBookHistory(@PathVariable Long bookId, @ModelAttribute Paginator paginator, Authentication auth, Model model) {
        paginator.setUsername(auth.getName());
        model.addAttribute("detail", historyService.findHistoryDetailsByBookId(bookId, paginator));
        return "book/histories";
    }

}