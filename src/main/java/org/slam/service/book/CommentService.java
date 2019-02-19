package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Comment;
import org.slam.mapper.book.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public List<Comment> selectCommentsByBookId(Long bookId) {
        return commentMapper.selectCommentsByBookId(bookId);
    }

    @Transactional
    public int insertComment(Comment comment) {
        if (comment.getParentId() != null && !comment.getParentId().equals(0L)) {
            commentMapper.updateGroupOrder(comment);
            return commentMapper.insertComment(comment);
        } else {
            return commentMapper.insertComment(comment);
        }
    }

    public int deleteComment(Long bookId, Long commentId) {
        return commentMapper.deleteComment(bookId, commentId);
    }
}
