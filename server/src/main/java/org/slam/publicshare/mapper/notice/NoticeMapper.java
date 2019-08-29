package org.slam.publicshare.mapper.notice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.slam.publicshare.dto.account.AccountDto;
import org.slam.publicshare.dto.book.BookHistory;

import java.util.List;

public interface NoticeMapper {

    Integer findTotalCount(AccountDto acc);

    List<BookHistory> findByUsername(String username);

    @Update("UPDATE BOOK_HISTORY SET IS_CHECKED = true, MODIFIED_BY = #{username}, MODIFIED_AT = NOW() WHERE ID = #{historyId}")
    int updateToChecked(@Param("historyId") Long historyId, @Param("username") String username);

}
