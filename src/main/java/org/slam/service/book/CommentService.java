package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Comment;
import org.slam.dto.common.Paginator;
import org.slam.mapper.book.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public Map<String, Object> findCommentsByBookId(Long bookId, Paginator paginator) {
        var result = new HashMap<String, Object>();
        paginator.setTotal(commentMapper.findTotalCount(bookId, paginator));

        result.put("comments", commentMapper.findCommentsByBookId(bookId, paginator));
        result.put("paginator", paginator);

        return result;
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
