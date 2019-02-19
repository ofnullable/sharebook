package org.slam.web;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Comment;
import org.slam.service.book.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/{bookId}")
    public int insertComment(Comment comment) {
        return commentService.insertComment(comment);
    }

    @DeleteMapping("/{bookId}/{commentId}")
    public int deleteComment(@PathVariable Long bookId, @PathVariable Long commentId) {
        return commentService.deleteComment(bookId, commentId);
    }

}
