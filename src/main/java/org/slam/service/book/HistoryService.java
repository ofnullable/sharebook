package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.mapper.book.HistoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryService {

    private final HistoryMapper historyMapper;

    public List<Book> selectMatchStatusHistory(BookStatus status, String username) {
        return historyMapper.selectMatchStatusHistory(status, username);
    }
}
