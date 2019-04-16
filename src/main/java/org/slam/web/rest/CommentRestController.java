package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Comment;
import org.slam.dto.common.Paginator;
import org.slam.service.book.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping("/{bookId}")
    public Map<String, Object> findCommentsByBookId(@PathVariable Long bookId, Paginator paginator) {
        return commentService.findCommentsByBookId(bookId, paginator);
    }

    @PostMapping("/{bookId}")
    public int insertComment(Comment comment) {
        return commentService.insertComment(comment);
    }

    @DeleteMapping("/{bookId}/{commentId}")
    public int deleteComment(@PathVariable Long bookId, @PathVariable Long commentId) {
        return commentService.deleteComment(bookId, commentId);
    }

}
