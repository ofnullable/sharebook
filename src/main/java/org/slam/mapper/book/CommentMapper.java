package org.slam.mapper.book;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.slam.dto.book.Comment;

import java.util.List;

public interface CommentMapper {

    List<Comment> selectCommentsByBookId(Long bookId);

    void updateGroupOrder(Comment comment);

    int insertComment(Comment comment);

    @Delete("DELETE FROM COMMENTS WHERE ID = #{id} AND BOOK_ID = #{bookId}")
    int deleteComment(@Param("bookId") Long bookId, @Param("id") Long commentId);

}
